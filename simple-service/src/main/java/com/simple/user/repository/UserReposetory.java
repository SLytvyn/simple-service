package com.simple.user.repository;

import com.simple.user.model.UserModel;

public interface UserReposetory {

    public UserModel getByEmail(String email);

    public void saveUser(UserModel userModel);
}
