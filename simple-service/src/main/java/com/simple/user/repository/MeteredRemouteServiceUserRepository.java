package com.simple.user.repository;

import com.simple.user.model.UserModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MeteredRemouteServiceUserRepository extends RemouteServiceUserRepository {

    @Override
    public UserModel getByEmail(String email) {
        var before = System.currentTimeMillis();
        var userByEmail = super.getByEmail(email);
        var after = System.currentTimeMillis();
        System.out.println("Time to get: " + (after - before));
        return userByEmail;
    }

    @Override
    public void saveUser(UserModel userModel) {
        var before = System.currentTimeMillis();
        super.saveUser(userModel);
        var after = System.currentTimeMillis();
        System.out.println("Time to save: " + (after - before));
    }

}
