package org.hibernate;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.Reader;

/**
 * Created by manlier on 2016/11/21.
 */

/**
 * 基础的测试类，
 * 它实现了SessionFactoryGenerator接口
 * 用于快速的获取SessionFactory对象，方便测试
 */
public class BasicTest implements SessionFactoryGenerator {

    protected SessionFactory sessionFactory;

    @Before
    public void setup() throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    @After
    public void teardown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }

    /**
     * 简化Session获取与操作方式
     *
     * @param generator 一个SessionFactoryGenerator对象，用于获得SessionFactory
     * @param action    一个动作对象，用于实际的数据库操作
     */
    protected void doInHibernate(SessionFactoryGenerator generator, Action action) {
        sessionFactory = generator.sessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        try {
            action.doAction(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    /**
     * 对于某些sql类型，如：Clob, NClob等
     * 进行方便的到字符串的转换
     *
     * @param reader 字符读取对象
     * @return 最终的字符串
     * @throws IOException 产生IO异常
     */
    protected String toString(Reader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024];
        int off = 0;
        while ((off = reader.read(buffer, off, buffer.length)) != -1) {
            builder.append(buffer);
        }
        return builder.toString();
    }

    @Override
    public SessionFactory sessionFactory() {
        return sessionFactory;
    }

    protected interface Action {

        void doAction(Session session) throws Exception;
    }
}
