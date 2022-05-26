package com.gmail.arthurstrokov.resumeproject.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class EmployeeNotFoundExceptionHandler {
    private static final String ERROR_MESSAGE = "message";

    @ExceptionHandler({IllegalArgumentException.class, EmployeeNotFoundException.class})
    public ResponseEntity<?> handle(Exception e) {
        return ResponseEntity.internalServerError()
                .body(Collections.singletonMap(ERROR_MESSAGE, e.getMessage()));
    }
}
