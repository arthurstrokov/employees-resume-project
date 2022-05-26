package com.gmail.arthurstrokov.resumeproject.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(long id) {
        super("Couldn't find employee " + id);
    }
}
