package com.boost.voucherpool.exception;

// This type of exception is thrown when unexpected programming error occurs
public class ProgrammingException extends RuntimeException {

    private String details;

    public ProgrammingException(String details) {
        this.details = details;
    }
}
