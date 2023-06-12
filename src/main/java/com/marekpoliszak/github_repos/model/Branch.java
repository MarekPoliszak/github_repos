package com.marekpoliszak.github_repos.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Branch {
    private String name;
    private Commit commit;
}
