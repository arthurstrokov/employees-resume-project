package com.gmail.arthurstrokov.resumeproject.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String id) {
        super("Couldn't find employee " + id);
    }
}
