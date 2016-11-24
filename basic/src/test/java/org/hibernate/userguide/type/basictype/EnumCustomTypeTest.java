package org.hibernate.userguide.type.basictype;

import org.hibernate.CustomTypeTest;
import org.junit.Test;

/**
 * Created by manlier on 2016/11/23.
 */
public class EnumCustomTypeTest extends CustomTypeTest {

    public EnumCustomTypeTest() {
        super(GenderType.INSTANCE);
    }

    @Test
    public void testCustomTypeMapping() {
        Person person = new Person();
        person.setId(1L);
        person.setName("Piece");
        person.setGender(Gender.MALE);

        doInHibernate(this, session -> session.save(person));

        doInHibernate(this, session -> System.out.println(session.find(Person.class, 1L)));
    }
}
