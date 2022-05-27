package com.gmail.arthurstrokov.resumeproject.exceptions;

/**
 * Exception class
 *
 * @author Arthur Strokov
 */
public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException() {
        super("Page not found");
    }
}
