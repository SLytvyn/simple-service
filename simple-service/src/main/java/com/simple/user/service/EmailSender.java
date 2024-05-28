package com.simple.user.service;

public class EmailSender {

    public void sendEmail(String email, String message) {
        System.out.printf("Thread: %s%n", Thread.currentThread().getName());
        System.out.printf("Sending mail message: %s to user: %s%n", message, email);
    }
}
