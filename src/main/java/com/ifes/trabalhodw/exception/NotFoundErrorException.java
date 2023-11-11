package com.ifes.trabalhodw.exception;

public class NotFoundErrorException extends RuntimeException{
    public NotFoundErrorException(String message) {
        super(message);
    }
}
