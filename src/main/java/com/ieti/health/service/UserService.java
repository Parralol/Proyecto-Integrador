package com.ieti.health.service;

import com.ieti.health.repository.User.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserService  extends MongoRepository<User, String>{

    public User findByName(String name);
    
}
