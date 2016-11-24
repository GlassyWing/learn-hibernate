package org.hibernate;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.BasicType;

import java.util.Arrays;
import java.util.List;

/**
 * Created by manlier on 2016/11/23.
 */
public class CustomTypeTest extends BasicTest {

    private List<BasicType> basicTypes;

    /**
     * 可注册自定义基本类型
     * @param basicTypes 自定义基本类型的数组
     */
    public CustomTypeTest(BasicType... basicTypes) {
        this.basicTypes = Arrays.asList(basicTypes);
    }

    @Override
    public void setup() throws Exception {
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();

        MetadataSources sources = new MetadataSources(registry);

        MetadataBuilder builder = sources.getMetadataBuilder();

        // 注册所有自定义基本类型
        basicTypes.forEach(builder::applyBasicType);

        try {
            sessionFactory = builder.build().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
