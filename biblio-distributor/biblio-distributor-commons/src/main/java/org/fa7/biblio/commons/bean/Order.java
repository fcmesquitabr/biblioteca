package org.fa7.biblio.commons.bean;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;
import java.util.UUID;

public class Order implements Serializable {

    private static final long serialVersionUID = 1084323863712019997L;

    private Long clientId;
    private String callBackUrl;
    private String clientOrderId;
    private String orderId;
    private Collection<Item> items;

    public Order() {
    }

    public Order(Long clientId, String callBackUrl, Collection<Item> items) {
        this.clientId = clientId;
        this.callBackUrl = callBackUrl;
        this.items = items;
    }

    public Long getClientId() {
        return clientId;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public String getClientOrderId() {
        return clientOrderId;
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
