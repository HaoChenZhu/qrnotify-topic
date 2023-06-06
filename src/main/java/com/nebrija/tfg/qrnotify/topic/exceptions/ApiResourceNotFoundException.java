package com.nebrija.tfg.qrnotify.topic.exceptions;

public class ApiResourceNotFoundException extends RuntimeException {

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

    public ApiResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
