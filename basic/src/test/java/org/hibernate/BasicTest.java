package org.hibernate;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;

/**
 * Created by manlier on 2016/11/21.
 */
public class BasicTest implements SessionFactoryGenerator {

    protected SessionFactory sessionFactory;

    @Before
    public void setup() throws Exception {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @After
    public void teardown() {
        if ( sessionFactory != null ) {
            sessionFactory.close();

        }
    }

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

    @Override
    public SessionFactory sessionFactory() {
        return sessionFactory;
    }

    protected interface Action {

        void doAction(Session session) throws Exception;
    }
}
