package com.black.mulberry.core.exception;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String message){
        super(message);
    }
}
