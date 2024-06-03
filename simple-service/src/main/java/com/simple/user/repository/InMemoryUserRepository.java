package com.simple.user.repository;

import com.simple.user.web.UserModel;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserReposetory {

    private final Map<String, UserModel> users = new HashMap<>();

    @Override
    public UserModel getByEmail(String email) {
        return users.get(email);
    }

    @Override
    public void saveUser(UserModel userModel) {
        users.put(userModel.getEmail(), userModel);
    }
}
