package org.fa7.biblio.consumer.bean;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "item")
public class Item implements Serializable {

    private static final long serialVersionUID = -8324710628604408212L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long isbn;
    private Integer amountRequest;
    private Integer amountResponse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public Item() {
    }

    public Item(org.fa7.biblio.commons.bean.Item item, Order order) {
        this.isbn = item.getIsbn();
        this.amountRequest = item.getAmount();
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public Integer getAmountRequest() {
        return amountRequest;
    }

    public void setAmountRequest(Integer amountRequest) {
        this.amountRequest = amountRequest;
    }

    public Integer getAmountResponse() {
        return amountResponse;
    }

    public void setAmountResponse(Integer amountResponse) {
        this.amountResponse = amountResponse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Item item = (Item) o;

        return isbn.equals(item.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
