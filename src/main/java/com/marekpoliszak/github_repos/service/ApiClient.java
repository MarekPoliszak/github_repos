package com.marekpoliszak.github_repos.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.Branch;
import com.marekpoliszak.github_repos.model.UserRepository;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class ApiClient {
    private final String api_key = System.getenv("GitHub_TOKEN");


    public HttpRequest configRequest(String url) {
        return HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + api_key)
                .build();
    }

    public ArrayList<UserRepository> sendRequestForRepositoryList(HttpRequest httpRequest) throws IOException, InterruptedException, UserNotFoundException {
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 404) {
            //throw new ResponseStatusException(HttpStatusCode.valueOf(response.statusCode()));
            throw new UserNotFoundException("Hello there! I am custom exception.");
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<UserRepository>>(){}.getType();
        return gson.fromJson(response.body(), listType);
    }

    public ArrayList<Branch> sendRequestForBranchList(HttpRequest httpRequest) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Branch>>(){}.getType();
        return gson.fromJson(response.body(), listType);
    }
}
