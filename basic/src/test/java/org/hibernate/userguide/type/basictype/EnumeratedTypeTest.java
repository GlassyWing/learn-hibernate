package org.hibernate.userguide.type.basictype;

import org.hibernate.BasicTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by manlier on 2016/11/22.
 */
public class EnumeratedTypeTest extends BasicTest {

    @Test
    public void testEnumType() {

        Phone phone = new Phone();
        phone.setId(1L);
        phone.setNumber("138-9047-9285");
        phone.setType(PhoneType.MOBILE);

        doInHibernate(this, session -> {
            session.save(phone);
        });

        doInHibernate(this, session -> {
            Phone phone1 = session.find(Phone.class, 1L);
            Assert.assertEquals(phone, phone1);
        });
    }

    @Test
    public void testEnumConverter() {
        Person person = new Person();
        person.setId(1L);
        person.setGender(Gender.MALE);
        person.setName("Piece");

        doInHibernate(this, session -> {
            session.save(person);
        });

        doInHibernate(this, session -> {
            System.out.println(session.find(Person.class, 1L));
        });
    }
}
