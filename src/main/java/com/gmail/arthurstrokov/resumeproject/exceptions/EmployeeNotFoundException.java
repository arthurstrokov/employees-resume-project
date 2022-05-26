package com.gmail.arthurstrokov.resumeproject.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Couldn't find employee " + id);
    }

    public EmployeeNotFoundException(String email) {
        super("Could not find employee " + email);
    }
}
