package org.fa7.biblio.commons.bean;


import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Collection;

public class Order implements Serializable {

    private static final long serialVersionUID = 1084323863712019997L;

    private Long id;
    private Long clientId;
    private String callBackUrl;
    private Collection<Item> items;

    public Order() {
    }

    public Order(Long clientId, String callBackUrl, Collection<Item> items) {
        this.clientId = clientId;
        this.callBackUrl = callBackUrl;
        this.items = items;
    }

    public Order(Long id, Long clientId, String callBackUrl, Collection<Item> items) {
        this.id = id;
        this.clientId = clientId;
        this.callBackUrl = callBackUrl;
        this.items = items;
    }

    public Long getId() {
        return id;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
