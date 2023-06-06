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
        String repoUrl = "https://api.github.com/users/" + username + "/repos";
        ArrayList<UserRepository> repositoryList = client.sendRequestForRepositoryList(client.configRequest(repoUrl));
        for(UserRepository repo: repositoryList) {
            String branchUrl = "https://api.github.com/repos/" + repo.getOwner().getLogin() + "/" + repo.getName() + "/branches";
            repo.setBranches(client.sendRequestForBranchList(client.configRequest(branchUrl)));
        }
        return repositoryList;
    }
}
