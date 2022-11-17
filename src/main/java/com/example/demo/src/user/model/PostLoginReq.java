package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.NamedEntityGraph;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostLoginReq {
    private String login_id;
    private String password;
}
