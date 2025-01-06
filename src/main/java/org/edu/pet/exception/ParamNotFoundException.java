package org.edu.pet.exception;

public class ParamNotFoundException extends RuntimeException {

    public ParamNotFoundException(String message) {
        super(message);
    }
}