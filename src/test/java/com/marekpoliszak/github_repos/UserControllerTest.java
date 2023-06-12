package com.marekpoliszak.github_repos;

import com.marekpoliszak.github_repos.controller.UserController;
import com.marekpoliszak.github_repos.exception.UserNotFoundException;
import com.marekpoliszak.github_repos.model.*;
import com.marekpoliszak.github_repos.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@ComponentScan("com.marekpoliszak.github_repos")
public class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;



    @Test
    public void testGetAllUserRepositories_Success() throws Exception {

        User user = new User();
        user.setUsername("validUser");

        ArrayList<UserRepository> userRepositoryList = new ArrayList<>();
        ArrayList<Branch> repositoryBranchList = new ArrayList<>();
        repositoryBranchList.add(new Branch("branchName1", new Commit("123456")));
        repositoryBranchList.add(new Branch("branchName2", new Commit("098776")));
        userRepositoryList.add(new UserRepository("repo1", new Owner("ownerName1"), repositoryBranchList));
        userRepositoryList.add(new UserRepository("repo2", new Owner("ownerName2"), repositoryBranchList));


        when(userService.getUserRepo(user.getUsername())).thenReturn(userRepositoryList);

        mockMvc.perform(MockMvcRequestBuilders.post("/repos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + user.getUsername() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("repo1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].owner.login").value("ownerName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[0].name").value("branchName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[0].commit.sha").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[1].name").value("branchName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].branches[1].commit.sha").value("098776"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("repo2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].owner.login").value("ownerName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches[0].name").value("branchName1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches[0].commit.sha").value("123456"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches[1].name").value("branchName2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].branches[1].commit.sha").value("098776"));
    }

    @Test
    public void testGetAllUserRepositories_UserNotFound() throws Exception {

        User user = new User();
        user.setUsername("nonExistingUser");

        when(userService.getUserRepo(user.getUsername())).thenThrow(new UserNotFoundException("No user is found with provided username"));

        mockMvc.perform(MockMvcRequestBuilders.post("/repos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + user.getUsername() + "\"}")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(404))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No user is found with provided username"));
    }

    @Test
    public void testGetAllUserRepositories_InvalidMediaType() throws Exception {

        User user = new User();
        user.setUsername("validUser");

        when(userService.getUserRepo(user.getUsername())).thenThrow(InvalidMediaTypeException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/repos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"" + user.getUsername() + "\"}")
                        .header("Accept", "application/xml"))
                .andExpect(status().isNotAcceptable())
                .andExpect(MockMvcResultMatchers.content().string("{\"status\": 406, \"message\": \"No acceptable representation\"}"));
    }
}
