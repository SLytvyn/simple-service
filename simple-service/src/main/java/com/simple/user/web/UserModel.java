package com.simple.user.web;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserModel {

    private String email;
    private String name;
}
