package com.bci.user_service.components.exceptions;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String message) {
        super(message);
    }
}
