package com.ivpo.beerchallenge.exception;

public class ResponseMessage {
    private final String text;

    public ResponseMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
