package com.marekpoliszak.github_repos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class UserException {
    private HttpStatus status;
    private String message;
}
