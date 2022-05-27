package com.gmail.arthurstrokov.resumeproject.exceptions;

/**
 * Exception class
 *
 * @author Arthur Strokov
 */
public class EmployeeAlreadyExistsException extends RuntimeException {

    public EmployeeAlreadyExistsException(String email) {
        super("Employee with " + email + " email already exists");
    }
}
