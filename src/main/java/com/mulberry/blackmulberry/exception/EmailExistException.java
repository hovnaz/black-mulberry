package com.mulberry.blackmulberry.exception;

public class EmailExistException extends RuntimeException {

    public EmailExistException(String message){
        super(message);
    }
}
