package com.simple.user.web;

import com.simple.user.model.UserModel;
import com.simple.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{id}")
    public UserModel getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public UserModel create(@RequestParam("name") String name, @RequestParam("email") String email) {
        return userService.createUser(name, email);
    }

}
