package com.simple.user.service;

import com.simple.user.model.UserCreatedEvent;
import com.simple.user.model.UserModel;
import com.simple.user.repository.InMemoryUserRepository;
import com.simple.user.repository.MeteredRemouteServiceUserRepository;
import com.simple.user.repository.SqlUserRepository;
import com.simple.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository sqlUserRepository = new SqlUserRepository();
    private final UserRepository inMemoryUserRepository = new InMemoryUserRepository();
    private final UserRepository remouteUserRepository = new MeteredRemouteServiceUserRepository();
    private final Supplier<String> idGenerator = () -> String.valueOf(System.currentTimeMillis());

    private final ApplicationEventPublisher applicationEventPublisher;

    public UserModel getUserById(String id) {
        return getUserRepositoryById(id).getUserById(id);
    }

    public UserModel createUser(String name, String email) {
        var id = idGenerator.get();
        var userModel = new UserModel(id, name, email);
        applicationEventPublisher.publishEvent(new UserCreatedEvent(this, userModel));
        getUserRepositoryById(id).saveUser(userModel);
        return userModel;
    }

    private UserRepository getUserRepositoryById(String id) {
        var charArray = id.toCharArray();
        return switch (charArray[charArray.length - 1]) {
            case '1' -> sqlUserRepository;
            case '2' -> inMemoryUserRepository;
            default -> remouteUserRepository;
        };
    }

}