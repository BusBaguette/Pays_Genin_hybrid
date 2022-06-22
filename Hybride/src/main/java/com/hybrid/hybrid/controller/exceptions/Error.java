package com.hybrid.hybrid.controller.exceptions;


import org.springframework.http.HttpStatus;

public class Error extends com.hybrid.hybrid.error.Error {
    public Error(String message, HttpStatus status) {
        super(message, status);
    }

    public Error(String message, HttpStatus status, Exception base) {
        super(message, status, base);
    }
}
