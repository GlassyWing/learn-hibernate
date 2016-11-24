package org.hibernate.userguide.type.basictype.lobtype;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Blob;

/**
 * Created by manlier on 2016/11/24.
 */
@Entity(name = "Product")
public class Product3 {

    @Id
    private Integer id;

    private String name;

    @Lob
    private Blob image;

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

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
