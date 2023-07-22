package com.huisarts.demo.exceptions;

public class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UserNotFoundException() {
        super("Kan specifieke klant niet vinden.");
    }
}
