package org.hibernate.userguide.type.basictype;

import org.hibernate.BasicTest;
import org.hibernate.userguide.type.basictype.nationalizedchardata.Product;
import org.hibernate.userguide.type.basictype.nationalizedchardata.Product1;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Reader;

import static org.junit.Assert.*;

/**
 * Created by manlier on 2016/11/24.
 */

/**
 * 测试Nationalized类型的映射方式
 */
public class NationalizedTest extends BasicTest {

    @Test
    public void testPersistNClobEntity() {
        String warranty = "My product warranty";

        final Product product = new Product();
        product.setId(1);
        product.setName("Mobile phone");

        doInHibernate(this, session -> {
            session.doWork(connection -> {
                product.setWarranty(connection.createNClob());
                product.getWarranty().setString(1, warranty);
            });

            session.persist(product);
        });

        doInHibernate(this, session -> {
            Product product1 = session.find(Product.class, 1);
            try (Reader reader = product1.getWarranty().getCharacterStream()) {
                assertEquals(warranty, toString(reader));
            }
        });
    }

    @Test
    public void testPersistNClobEntityBaseOnMaterialized() {
        String warranty = "My product warranty";

        final Product1 product = new Product1();
        product.setId(1);
        product.setName("Mobile phone");
        product.setWarranty(warranty.toCharArray());

        doInHibernate(this, session -> {
            session.persist(product);
        });

        doInHibernate(this, session -> {
            Product1 product1 = session.find(Product1.class, 1);
            assertEquals(warranty, new String(product.getWarranty()));
        });
    }
}
