package com.marekpoliszak.github_repos.exception;

import com.marekpoliszak.github_repos.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        ApiException apiException = new ApiException(404, e.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Object>  handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        String response = "{\"status\": " + e.getStatusCode().value() + ", \"message\": \"" + e.getMessage() + "\"}";
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }
}
