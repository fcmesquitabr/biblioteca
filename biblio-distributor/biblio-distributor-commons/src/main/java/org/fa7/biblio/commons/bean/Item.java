package org.fa7.biblio.commons.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Item implements Serializable {

    private static final long serialVersionUID = -871307626480832199L;

    private Long id;
    private Long isbn;
    private Integer amount;

    public Item() {
    }

    public Item(Long isbn, Integer amount) {
        this.isbn = isbn;
        this.amount = amount;
    }

    public Item(Long id, Long isbn, Integer amount) {
        this.id = id;
        this.isbn = isbn;
        this.amount = amount;
    }

    public Long getIsbn() {
        return isbn;
    }

    public Long getId() {
        return id;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
