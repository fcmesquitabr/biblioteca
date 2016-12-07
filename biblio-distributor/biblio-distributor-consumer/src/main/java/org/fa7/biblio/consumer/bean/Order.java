package org.fa7.biblio.consumer.bean;


import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "order_request")
public class Order implements Serializable {

    private static final long serialVersionUID = 5153742269095335643L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private String callBackUrl;
    private Date requestDate;
    private String clientOrderId;
    private String orderId;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "order", fetch = FetchType.LAZY)
    private List<Item> items;

    public Order() {
    }

    public Order(org.fa7.biblio.commons.bean.Order order) {
        this.clientId = order.getClientId();
        this.callBackUrl = order.getCallBackUrl();
        this.items = order.getItems().stream().map(i -> new Item(i, this)).collect(Collectors.toList());
        this.orderId = order.getOrderId();
        this.clientOrderId = order.getClientOrderId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
