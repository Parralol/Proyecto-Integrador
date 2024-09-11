package com.ieti.health.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ieti.health.repository.User.User;
import com.ieti.health.repository.User.UserDto;
import com.ieti.health.service.UserService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UserController {

    @SuppressWarnings("unused")
    private final UserService usersService;

    public UserController(@Autowired UserService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto dto) {
        URI createdUserUri = URI.create("");
        User user = new User(dto);

        usersService.save(user);
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(new ArrayList<User>(usersService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        System.out.println(id);
        return ResponseEntity.ok(usersService.findById(id).get());
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@PathVariable("id") String id, @RequestBody UserDto dto) {
        User user = usersService.findById(id).get();
        user.setEmail(dto.getEmail());
        user.setLastName(dto.getLastName());
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

