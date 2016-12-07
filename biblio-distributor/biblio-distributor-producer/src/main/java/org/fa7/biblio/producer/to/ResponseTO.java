package org.fa7.biblio.producer.to;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseTO {

    private final Boolean success;
    private final String message;
    private final String orderId;

    public ResponseTO(Boolean success, String message, String orderId) {
        this.success = success;
        this.message = message;
        this.orderId = orderId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getOrderId() {
        return orderId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
