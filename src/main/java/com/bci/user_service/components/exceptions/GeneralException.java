package com.bci.user_service.components.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException{
    private final HttpStatus httpStatusCode;


    public GeneralException(String message, HttpStatus httpStatusCode) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}
