package org.hibernate.userguide.type.basictype.lobtype;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * Created by manlier on 2016/11/24.
 */
@Entity(name = "Product")
public class Product4 {

    @Id
    private Integer id;

    private String name;

    @Lob
    private byte[] image;

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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
