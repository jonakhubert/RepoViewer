package com.example.repoviewer;

import com.example.repoviewer.Models.Responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException() {
        return buildErrorResponse("User not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<ErrorResponse> handleExceedRateLimit() {
        return buildErrorResponse("Exceed rate limit", HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleMethodNotSupportedException() {
        return buildErrorResponse("Method not supported", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpClientErrorException.UnsupportedMediaType.class)
    public ResponseEntity<ErrorResponse> handleBusinessException() {
        return buildErrorResponse("Media type not acceptable", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus httpStatus) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
