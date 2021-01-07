package com.tamiflu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String uid;
    private String username;
    private String password;
    private String nickname;
    private String userType;
    private Teacher teacher;
}
