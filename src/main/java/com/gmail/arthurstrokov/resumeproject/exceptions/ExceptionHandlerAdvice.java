package com.gmail.arthurstrokov.resumeproject.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

/**
 * Exception handler class
 *
 * @author Arthur Strokov
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    private static final String ERROR_MESSAGE = "message";

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgument(Exception exception) {
        return ResponseEntity.internalServerError().body(Collections.singletonMap(ERROR_MESSAGE, exception.getMessage()));
    }

    @ExceptionHandler({EmployeeNotFoundException.class})
    public ResponseEntity<?> handleNotFound(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap(ERROR_MESSAGE, exception.getMessage()));
    }

    @ExceptionHandler({EmployeeAlreadyExistsException.class})
    public ResponseEntity<?> handleBadRequest(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap(ERROR_MESSAGE, exception.getMessage()));
    }
}
