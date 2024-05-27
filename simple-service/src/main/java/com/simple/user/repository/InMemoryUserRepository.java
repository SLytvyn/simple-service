package com.simple.user.repository;

import com.simple.user.model.UserModel;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, UserModel> users = new HashMap<>();

    @Override
    public UserModel getUserById(String id) {
        return users.get(id);
    }

    @Override
    public void saveUser(UserModel userModel) {
        users.put(userModel.getId(), userModel);
    }
}
