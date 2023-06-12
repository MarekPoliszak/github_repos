# github_repos
[Spring Boot](http://projects.spring.io/spring-boot/) application that helps you to get user's git repositories using GitHub API 

## Requirements
For building and running the application you need:
- [JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [MAVEN 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.marekpoliszak.github_repos.GithubReposApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

API is sending requests to [GitHub API](https://developer.github.com/v3) therefore it's request must be authenticated by using personal 
access token. Create a personal access token and save it in your environment variables under the name GitHub_TOKEN.

## API's endpoints

There is one endpoint you can access:
POST /repos: return user's repositories based on provided username.

Example: 
```curl
curl -X POST -H "Content-Type: application/json" -d '{"username": "someUsername"}' http://localhost:8080/repos
```
