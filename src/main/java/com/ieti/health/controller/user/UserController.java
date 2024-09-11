package com.ieti.health.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ieti.health.repository.User.Document;
import com.ieti.health.repository.User.DocumentDto;
import com.ieti.health.service.DocumentService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @SuppressWarnings("unused")
    private final DocumentService usersService;

    public UserController(@Autowired DocumentService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Document> createUser(@RequestBody DocumentDto dto) {
        URI createdUserUri = URI.create("");
        Document user = new Document(dto);

        usersService.save(user);
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Document>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<Document>(usersService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<Document> findById(@PathVariable("id") String id) {
        System.out.println(id);
        return ResponseEntity.ok(usersService.findById(id).get());
    }

    @PutMapping
    public ResponseEntity<Document> updateUser(@PathVariable("id") String id, @RequestBody DocumentDto dto) {
        Document user = usersService.findById(id).get();
        user.setEmail(dto.getEmail());
        user.setBody(dto.getBody());
        user.setName(dto.getName());
        usersService.save(user);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String id) {
        usersService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

