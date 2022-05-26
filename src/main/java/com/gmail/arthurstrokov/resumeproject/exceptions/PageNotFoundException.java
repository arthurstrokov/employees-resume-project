package com.gmail.arthurstrokov.resumeproject.exceptions;

public class PageNotFoundException extends RuntimeException {

    public PageNotFoundException() {
        super("Page not found");
    }
}
