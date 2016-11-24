package org.hibernate.userguide.type.basictype;

import javax.persistence.Table;

import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.BitSet;

/**
 * Created by manlier on 2016/11/21.
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    @Id
    private Integer id;

    @Type(type = "bitset")
    private BitSet bitSet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BitSet getBitSet() {
        return bitSet;
    }

    public void setBitSet(BitSet bitSet) {
        this.bitSet = bitSet;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", bitSet=" + bitSet +
                '}';
    }
}
