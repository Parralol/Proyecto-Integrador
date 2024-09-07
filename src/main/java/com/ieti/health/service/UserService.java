package com.ieti.health.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ieti.health.repository.User.User;

@Service
public class UserService {
    Map<String, User> users = new HashMap<>();

    public void save(User user){
        users.put(user.getId(), user);
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public User getUser(String id){
        return users.get(id);
    }
    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    public User updateUser(User user, String userId){
        return users.put(userId, user);
    }

    public void deleteById(String id){
        users.remove(id);
    }
}
