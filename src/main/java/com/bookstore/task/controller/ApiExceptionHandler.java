package com.bookstore.task.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import com.bookstore.task.exception.BookNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(BookNotFoundException ex) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError("BOOK_NOT_FOUND", ex.getMessage(),null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex,
        HttpServletRequest request) {

       Map<String,String> errors = new LinkedHashMap<>();

       ex.getBindingResult().getFieldErrors().forEach(err -> {
        String field = err.getField();
        String message = err.getDefaultMessage(); 
        errors.putIfAbsent(field, message);
    });
    
    ApiError error = new ApiError(
        "VALIDATION_ERROR",
        "Invalid request body",
        errors
);

return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<ApiError> handleOptimisticLock(OptimisticLockingFailureException ex) {
        Map<String,String> errors = Map.of("version",ex.getMessage() );

        return ResponseEntity.status(
                HttpStatus.CONFLICT)
                .body(new ApiError("CONCURRENT_UPDATE",
                        "The resource was updated by another request. Please refresh and try again.",errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiError("INTERNAL_ERROR", "Something went wrong",null));
    }

}
