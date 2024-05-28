package com.simple.user.service;

import com.simple.user.model.UserModel;
import com.simple.user.repository.InMemoryUserRepository;
import com.simple.user.repository.MeteredRemouteServiceUserRepository;
import com.simple.user.repository.SqlUserRepository;
import com.simple.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository sqlUserRepository = new SqlUserRepository();
    private final UserRepository inMemoryUserRepository = new InMemoryUserRepository();
    private final UserRepository remouteUserRepository = new MeteredRemouteServiceUserRepository();
    private final EmailSender emailSender = new EmailSender();

    public UserModel getUserByEmail(String id) {
        return getUserRepositoryByEmail(id).getByEmail(id);
    }

    public UserModel createUser(String name, String email) {
        var userModel = new UserModel(name, email);
        sendEmailOnCreate(userModel);
        getUserRepositoryByEmail(email).saveUser(userModel);
        return userModel;
    }

    /**
     * NOTE: Business request that user data source should be selected by email
     */
    private UserRepository getUserRepositoryByEmail(String email) {
        if (email.endsWith("@gmail.com")) {
            return sqlUserRepository;
        } else if (email.endsWith("@icloud.com")) {
            return remouteUserRepository;
        } else {
            return inMemoryUserRepository;
        }
    }

    @Async
    protected void sendEmailOnCreate(UserModel userModel) {
        emailSender.sendEmail(userModel.getEmail(), "User created: " + userModel.getName());
    }

}
