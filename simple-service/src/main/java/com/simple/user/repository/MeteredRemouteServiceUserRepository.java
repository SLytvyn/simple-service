package com.simple.user.repository;

import com.simple.user.model.UserModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeteredRemouteServiceUserRepository extends RemouteServiceUserRepository {

    @Override
    public UserModel getUserById(String id) {
        var before = System.currentTimeMillis();
        var userById = super.getUserById(id);
        var after = System.currentTimeMillis();
        System.out.println("Time to get: " + (after - before));
        return userById;
    }

    @Override
    public void saveUser(UserModel userModel) {
        var before = System.currentTimeMillis();
        super.saveUser(userModel);
        var after = System.currentTimeMillis();
        System.out.println("Time to save: " + (after - before));
    }

}
