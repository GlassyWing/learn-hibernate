package org.hibernate.userguide.type.basictype.nationalizedchardata;

import org.hibernate.annotations.Nationalized;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.NClob;

/**
 * Created by manlier on 2016/11/24.
 */
@Entity(name = "Product")
public class Product {

    @Id
    private Integer id;

    private String name;

    @Lob
    @Nationalized
    // Clob also works, because NClob extends Clob.
    // The database type is still NCLOB either way and handled as such.
    private NClob warranty;

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

    public NClob getWarranty() {
        return warranty;
    }

    public void setWarranty(NClob warranty) {
        this.warranty = warranty;
    }
}
