package com.ieti.health.service;

import com.ieti.health.repository.User.Document;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentService  extends MongoRepository<Document, String>{

    public Document findByName(String name);
    
    public Optional<Document> findByEmail(String email);
}
