package com.gmail.arthurstrokov.resumeproject.exceptions;

/**
 * Exception class
 *
 * @author Arthur Strokov
 */
public class EmployeeNotFoundException extends RuntimeException {
    /**
     * EmployeeNotFoundException
     *
     * @param id id
     */
    public EmployeeNotFoundException(Long id) {
        super("Couldn't find employee " + id);
    }

    public EmployeeNotFoundException(String email) {
        super("Could not find employee " + email);
    }
}
