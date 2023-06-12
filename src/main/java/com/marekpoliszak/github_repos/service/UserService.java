package com.marekpoliszak.github_repos.service;

import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private ApiClient client;

    private static String urlForRepositoryList(String username) {
        return "https://api.github.com/users/" + username + "/repos";
    }

    private static String urlForBranchList(String ownerLogin, String repositoryName) {
        return "https://api.github.com/repos/" + ownerLogin + "/" + repositoryName + "/branches";
    }

    public ArrayList<UserRepository> getUserRepo(String username) throws IOException, InterruptedException, UserNotFoundException {
        String repoUrl = urlForRepositoryList(username);
        ArrayList<UserRepository> repositoryList = client.sendRequestForRepositoryList(client.configRequest(repoUrl));
        for(UserRepository repo: repositoryList) {
            String branchUrl = urlForBranchList(repo.getOwner().getLogin(), repo.getName());
            repo.setBranches(client.sendRequestForBranchList(client.configRequest(branchUrl)));
        }
        return repositoryList;
    }
}
