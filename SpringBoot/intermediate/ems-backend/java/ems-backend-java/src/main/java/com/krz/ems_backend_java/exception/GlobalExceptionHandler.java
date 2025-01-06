package com.krz.ems_backend_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  //Generic exception handler
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleGenericException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Handle ResourceNotFoundException globally
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    // Create the error response with timestamp
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage(),
        request.getDescription(false));

    // Return the error response with additional message
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  // Handle DuplicateEntryException globally
  @ExceptionHandler(DuplicateEntryException.class)
  public ResponseEntity<?> handleDuplicateEntryException(DuplicateEntryException ex, WebRequest request) {
    // Create the error response with timestamp
    ErrorDetails errorDetails = new ErrorDetails(
        LocalDateTime.now(),
        HttpStatus.CONFLICT.value(),
        ex.getMessage(),
        request.getDescription(false));

    // Return the error response with additional message
    return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
  }

}
