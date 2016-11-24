package org.hibernate.userguide.type.basictype;

import org.hibernate.BasicTest;
import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.BitSet;

import static org.junit.Assert.*;

/**
 * Created by manlier on 2016/11/21.
 */

/**
 * 测试基本类型的使用
 * 包括自定义Hibernate的基本类型
 */
public class BitSetTypeTest extends BasicTest {

    @Override
    public void setup() throws Exception {
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();
        MetadataSources sources = new MetadataSources(registry);

        MetadataBuilder metadataBuilder = sources.getMetadataBuilder();

        // 注册自定义基本类型
        metadataBuilder.applyBasicType(BitSetType.INSTANCE);

        try {
            sessionFactory = metadataBuilder.build().buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test
    public void test() {

        BitSet bitSet = BitSet.valueOf( new long[] {1, 2, 3} );

        doInHibernate(this, session -> {
            Product product = new Product();
            product.setId(1);
            product.setBitSet(bitSet);
            session.save(product);
        });

        doInHibernate(this, session -> {
            Product product = session.get( Product.class, 1 );
            assertEquals(bitSet, product.getBitSet());
        } );

    }
}