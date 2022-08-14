package com.example.todebjavaspringcreditapplicationproject.exception;

public class NationalIdAlreadyExistsException extends RuntimeException {
    public NationalIdAlreadyExistsException() {
        super("Sorry customer of this national Id already exists.");
    }
}
