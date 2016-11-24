package org.hibernate.userguide.type.basictype.lobtype;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by manlier on 2016/11/24.
 */
@Entity(name = "Product")
public class Product2 {

    @Id
    private Integer id;

    private String name;

    @Lob
    private String warranty;

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

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
