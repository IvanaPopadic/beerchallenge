package com.ivpo.beerchallenge.exception;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

public class ResponseStatus {

    private Boolean status;

    private List<ResponseMessage> messages;

    private OffsetDateTime timestamp;

    public ResponseStatus(Boolean status,  OffsetDateTime timestamp) {
        this.status = status;
        this.timestamp = timestamp;
    }

    public ResponseStatus(Boolean status, List<ResponseMessage> messages, OffsetDateTime timestamp) {
        this.status = status;
        this.messages = messages;
        this.timestamp = timestamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ResponseMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ResponseMessage> messages) {
        this.messages = messages;
    }

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
