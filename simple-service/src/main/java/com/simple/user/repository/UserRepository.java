package com.simple.user.repository;

import com.simple.user.model.UserModel;

public interface UserRepository {

    UserModel getUserById(String id);

    void saveUser(UserModel userModel);
}
