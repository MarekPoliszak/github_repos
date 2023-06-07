package com.marekpoliszak.github_repos.exception;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(){}

    public UserNotFoundException(String message) {
        super(message);
    }
}
