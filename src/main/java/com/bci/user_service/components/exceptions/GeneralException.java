package com.bci.user_service.components.exceptions;

import org.springframework.http.HttpStatus;

public class GeneralException extends RuntimeException{
    private final HttpStatus status;


    public GeneralException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
//    public ResponseEntity<Object> customResponse(GeneralException ex) {
//
//        return new ResponseEntity<>(new ErrorResponseDto(ex.getMessage()), HttpStatus.BAD_REQUEST);
//    }
}
