package org.hibernate.userguide.type.basictype;

import org.hibernate.BasicTest;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.engine.jdbc.ClobProxy;
import org.hibernate.userguide.type.basictype.lobtype.*;
import org.junit.Test;

import java.io.Reader;

import static org.junit.Assert.*;

/**
 * Created by manlier on 2016/11/24.
 */
public class LobTypeTest extends BasicTest {

    @Test
    public void testLobTypeBaseOnLocator() {
        String warranty = "My product warranty";

        final org.hibernate.userguide.type.basictype.lobtype.Product product = new org.hibernate.userguide.type.basictype.lobtype.Product();
        product.setId(1);
        product.setName("Mobile phone");

        doInHibernate(this, session -> {
            session.doWork(connection -> product.setWarranty(
                    ClobProxy.generateProxy(warranty)
            ));
            session.save(product);
        });

        doInHibernate(this, session -> {
            org.hibernate.userguide.type.basictype.lobtype.Product product1 = session.find(org.hibernate.userguide.type.basictype.lobtype.Product.class, 1);
            try (Reader reader = product1.getWarranty().getCharacterStream()) {
                assertEquals("My product warranty", toString(reader));
            }
        });
    }

    @Test
    public void testBlobTypeBaseOnLocator() {
        byte[] image = new byte[]{1, 2, 3};

        final Product3 product = new Product3();
        product.setId(1);
        product.setName("Mobile phone");
        product.setImage(BlobProxy.generateProxy(image));

        doInHibernate(this, session -> session.persist(product));

        doInHibernate(this, session -> {
            Product3 product1 = session.find(Product3.class, 1);
            assertArrayEquals(new byte[]{1, 2, 3}, product1.getImage().getBytes(0, 3));
        });
    }

    @Test
    public void testLobTypeBaseOnMaterialized() {
        String warranty = "My product warranty";

        Product2 product2 = new Product2();
        product2.setId(1);
        product2.setName("Mobile phone");
        product2.setWarranty(warranty);

        doInHibernate(this, session -> session.save(product2));
        doInHibernate(this, session -> {
            Product2 product = session.find(Product2.class, 1);
            assertEquals(warranty, product.getWarranty());
        });
    }

    @Test
    public void testBlobTypeBaseOnMaterialized() {
        byte[] image = new byte[]{1, 2, 3};

        Product4 product = new Product4();
        product.setId(1);
        product.setName("Mobile phone");
        product.setImage(image);

        doInHibernate(this, session -> session.persist(image));

        doInHibernate(this, session -> {
            Product4 product1 = session.find(Product4.class, 1);
            assertArrayEquals(image, product1.getImage());
        });
    }

}
