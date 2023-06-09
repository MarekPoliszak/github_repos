package com.marekpoliszak.github_repos.controller;

import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.ApiException;
import com.marekpoliszak.github_repos.model.User;
import com.marekpoliszak.github_repos.model.UserRepository;
import com.marekpoliszak.github_repos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/repos")
public class UserController {

    @Autowired
    private UserService service;



    @PostMapping
    public ResponseEntity<Object> getAllUserRepositories(@RequestBody User user, @RequestHeader(required = false, value = "Accept") String acceptHeader) throws IOException, InterruptedException, UserNotFoundException, NotAcceptableException {
        if(acceptHeader!= null && acceptHeader.contains("application/xml")) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ApiException(406, "Can't accept XML"));
        }
        List<UserRepository> userRepositoryList = service.getUserRepo(user.getUsername());
        return ResponseEntity.ok(userRepositoryList);
        }
    }

