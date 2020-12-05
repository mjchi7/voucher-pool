package com.boost.voucherpool.exception;

public class EmailAlreadyExistException extends RuntimeException {

    private String email;

    public EmailAlreadyExistException(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "The email is already exists in the system. {" +
                "email='" + email + '\'' +
                '}';
    }
}
