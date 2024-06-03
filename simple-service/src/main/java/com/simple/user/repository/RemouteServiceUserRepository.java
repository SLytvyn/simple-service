package com.simple.user.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.user.web.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class RemouteServiceUserRepository implements UserReposetory {

    private volatile RestTemplate restTemplate = new RestTemplate();
    private volatile ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserModel getByEmail(String email) {
        return restTemplate.execute(System.getProperty("UserServiceHost") + "/users/" + email, HttpMethod.GET, null, response -> {
            if (response.getStatusCode().is2xxSuccessful()) {
                return objectMapper.readValue(response.getBody(), UserModel.class);
            } else {
                return null;
            }
        });
    }

    @Override
    public void saveUser(UserModel userModel) {
        restTemplate.postForObject("http://user-service/users", userModel, UserModel.class);
    }
}
