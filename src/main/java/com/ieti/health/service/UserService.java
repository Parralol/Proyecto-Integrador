package com.ieti.health.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ieti.health.controller.User.User;

@Service
public class UserService {
    Map<String, User> users = new HashMap<>();

    public void createUser(User user){

    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }
    


}
