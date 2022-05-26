package com.gmail.arthurstrokov.resumeproject.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Couldn't find employee " + id);
    }
}
