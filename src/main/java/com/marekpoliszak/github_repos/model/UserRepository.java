package com.marekpoliszak.github_repos.model;

import lombok.*;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class UserRepository {
    private String name;
    private Owner owner;
    private ArrayList<Branch> branches;
}
