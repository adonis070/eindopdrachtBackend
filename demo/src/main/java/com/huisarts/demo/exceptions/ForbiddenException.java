package com.huisarts.demo.exceptions;

public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super("Je hebt niet de juiste authorisatie voor deze handeling");
    }
}
