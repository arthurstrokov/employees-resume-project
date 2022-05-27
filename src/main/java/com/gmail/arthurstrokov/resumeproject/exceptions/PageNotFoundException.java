package com.gmail.arthurstrokov.resumeproject.exceptions;

/**
 * Exception class
 *
 * @author Arthur Strokov
 */
public class PageNotFoundException extends RuntimeException {
    /**
     * PageNotFoundException
     */
    public PageNotFoundException() {
        super("Page not found");
    }
}
