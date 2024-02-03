package com.dexa.exception;

public class DexaException extends RuntimeException {
    public DexaException(String errorMessage) {
        super(errorMessage);
    }
    public DexaException(String errorMessage, Throwable t) {
        super(errorMessage, t);
    }
}
