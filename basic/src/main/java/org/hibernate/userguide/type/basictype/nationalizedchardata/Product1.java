package org.hibernate.userguide.type.basictype.nationalizedchardata;

/**
 * Created by manlier on 2016/11/24.
 */

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * This one use the materialized way
 * 该类不同于Product类，
 * 其中的NClob属性采用物化的方式：即使用基本java类型
 */
@Entity(name = "Product")
public class Product1 {

    @Id
    private Integer id;

    private String name;

    @Lob
    @Nationalized
    private char[] warranty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char[] getWarranty() {
        return warranty;
    }

    public void setWarranty(char[] warranty) {
        this.warranty = warranty;
    }
}
