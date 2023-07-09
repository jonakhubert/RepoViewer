package com.example.repoviewer.Advice;

import com.example.repoviewer.Responses.ExceptionResponse;
import com.example.repoviewer.Exceptions.UnsupportedMediaTypeException;
import org.springframework.http.*;
import org.springframework.lang.NonNull;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            @NonNull HttpRequestMethodNotSupportedException ex, @NonNull HttpHeaders headers,
            @NonNull HttpStatusCode status, @NonNull WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.METHOD_NOT_ALLOWED.value(), "Method not allowed");

        return handleExceptionInternal(ex, response, setHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(UnsupportedMediaTypeException.class)
    public ResponseEntity<Object> handleUnsupportedMediaTypeException(UnsupportedMediaTypeException ex,WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage());

        return handleExceptionInternal(ex, response, setHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Object> handleUserNotFoundException(HttpClientErrorException.NotFound ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.NOT_FOUND.value(), "User not found");

        return handleExceptionInternal(ex, response, setHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<Object> handleExceedRateLimitException(HttpClientErrorException.Forbidden ex, WebRequest request) {
        ExceptionResponse response = new ExceptionResponse(ZonedDateTime.now(ZoneId.of("Z")),
                HttpStatus.FORBIDDEN.value(), "Exceed rate limit");

        return handleExceptionInternal(ex, response, setHeaders(), HttpStatus.FORBIDDEN, request);
    }

    private HttpHeaders setHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
