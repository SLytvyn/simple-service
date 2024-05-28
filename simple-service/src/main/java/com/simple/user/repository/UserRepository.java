package com.simple.user.repository;

import com.simple.user.model.UserModel;

public interface UserRepository {

    UserModel getByEmail(String email);

    void saveUser(UserModel userModel);
}
