package com.marekpoliszak.github_repos.service;

import com.marekpoliszak.github_repos.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    ApiClient client;

    public ArrayList<UserRepository> getUserRepo(String username) throws IOException, InterruptedException {
        String url = "https://api.github.com/users/" + username + "/repos";
        return client.sendRequest(client.configRequest(url));
    }
}
