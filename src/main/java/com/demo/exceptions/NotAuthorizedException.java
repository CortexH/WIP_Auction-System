package com.demo.exceptions;

public class NotAuthorizedException extends RuntimeException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
