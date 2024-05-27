package com.simple.user.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class RemouteServiceUserRepository implements UserRepository {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public UserModel getUserById(String id) {
        return restTemplate.execute("http://user-service/users/" + id, HttpMethod.GET, null, response -> {
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
