package org.fa7.biblio.producer.to;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseTO {

    private final Boolean success;
    private final String message;

    public ResponseTO(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
