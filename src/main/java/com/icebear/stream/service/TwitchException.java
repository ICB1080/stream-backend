package com.icebear.stream.service;

public class TwitchException extends RuntimeException {
    public TwitchException(String errorMessage) {
        super(errorMessage);
    }
}
