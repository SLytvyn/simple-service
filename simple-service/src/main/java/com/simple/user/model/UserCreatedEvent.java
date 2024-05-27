package com.simple.user.model;

import org.springframework.context.ApplicationEvent;

public class UserCreatedEvent extends ApplicationEvent {

    private final UserModel userModel;

    public UserCreatedEvent(Object source, UserModel userModel) {
        super(source);
        this.userModel = userModel;
    }
}
