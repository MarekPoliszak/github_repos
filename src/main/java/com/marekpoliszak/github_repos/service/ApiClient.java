package com.marekpoliszak.github_repos.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

    public ArrayList<UserRepository> sendRequest(HttpRequest httpRequest) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<UserRepository>>(){}.getType();
        if (response.statusCode() != 200) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(response.statusCode()));
        }
        return gson.fromJson(response.body(), listType);
    }
}
