package com.gmail.arthurstrokov.resumeproject.exceptions;

/**
 * Exception class
 *
 * @author Arthur Strokov
 */
public class ResourceAlreadyExistsException extends RuntimeException {
    /**
     * ResourceAlreadyExistsException
     *
     * @param message message
     */
    public ResourceAlreadyExistsException(String message) {
        super("Object already exists: " + message);
    }
}
