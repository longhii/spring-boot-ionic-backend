package com.gabriel.mc.services.exceptions;

public class ObjecNotFoundException extends RuntimeException {

    public ObjecNotFoundException(String msg) {
        super(msg);
    }

    public ObjecNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
