package com.marekpoliszak.github_repos.exception;

import com.marekpoliszak.github_repos.model.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
        UserException userException = new UserException(HttpStatus.NOT_FOUND, e.getMessage());
        return new ResponseEntity<>(userException, HttpStatus.NOT_FOUND);
    }
}
