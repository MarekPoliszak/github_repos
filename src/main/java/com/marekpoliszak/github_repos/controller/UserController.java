package com.marekpoliszak.github_repos.controller;

import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.User;
import com.marekpoliszak.github_repos.model.UserRepository;
import com.marekpoliszak.github_repos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/repos", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService service;



    @PostMapping
    public ResponseEntity<Object> getAllUserRepositories(@RequestBody User user, @RequestHeader(required = false, value = "Accept") String acceptHeader) throws IOException, InterruptedException, UserNotFoundException, HttpMediaTypeNotAcceptableException {
        try {
        List<UserRepository> userRepositoryList = service.getUserRepo(user.getUsername());
        return ResponseEntity.ok(userRepositoryList);
        } catch (InvalidMediaTypeException e) {
            throw new HttpMediaTypeNotAcceptableException(e.getMessage());
        }
    }
}

