package org.hibernate.userguide.type.basictype;

import org.hibernate.annotations.Type;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by manlier on 2016/11/23.
 */
@Entity(name = "Person")
public class Person {

    @Id
    private Long id;

    private String name;

    @Convert(converter = GenderConverter.class)
//    @Type(type = "org.hibernate.userguide.type.basictype.GenderType")
    private Gender gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
