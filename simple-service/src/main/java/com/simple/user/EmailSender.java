package com.simple.user;

import org.springframework.web.client.RestTemplate;

public class EmailSender {

    private final RestTemplate restTemplate = new RestTemplate();
    public void sendEmail(String email, String message) {
        restTemplate.postForEntity("http://localhost:8080/sendEmail/" + email, message, String.class);
    }
}
