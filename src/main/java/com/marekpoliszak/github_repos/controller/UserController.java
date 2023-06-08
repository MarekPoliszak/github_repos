package com.marekpoliszak.github_repos.controller;

import com.marekpoliszak.github_repos.exception.NotAcceptableException;
import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.User;
import com.marekpoliszak.github_repos.model.UserRepository;
import com.marekpoliszak.github_repos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public ResponseEntity<List<UserRepository>> getAllUserRepositories(@RequestBody User user, @RequestHeader(required = false, value = "Accept") String acceptHeader) throws IOException, InterruptedException, UserNotFoundException, NotAcceptableException {
        if(acceptHeader!= null && acceptHeader.contains("application/xml")) {
            throw new NotAcceptableException("Not acceptable representation");
        }
        List<UserRepository> userRepositoryList = service.getUserRepo(user.getUsername());
        return ResponseEntity.ok(userRepositoryList);
        }
    }

