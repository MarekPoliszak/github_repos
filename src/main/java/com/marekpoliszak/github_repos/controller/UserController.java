package com.marekpoliszak.github_repos.controller;

import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.User;
import com.marekpoliszak.github_repos.model.UserRepository;
import com.marekpoliszak.github_repos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/repos")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    public ArrayList<UserRepository> getAllUserRepositories(@RequestBody User user) throws IOException, InterruptedException, UserNotFoundException {
        return service.getUserRepo(user.getUsername());
    }
}
