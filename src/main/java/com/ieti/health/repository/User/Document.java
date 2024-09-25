package com.ieti.health.repository.User;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@org.springframework.data.mongodb.core.mapping.Document("document")
public class Document implements Serializable{

    @Id
    private String id;

    private  Date createdAt;
    private String name;
    private String body;
    private String email;
    @SuppressWarnings("unused")
    private String passwordHash;
    private List<RoleEnum> roles;

    public void setId(String id) {
        this.id = id;
    }


    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }


    public Document(){
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }
    
    public Document(String id, String name, String body, String email, String password) {
        this.id = id;
        this.name = name;
        this.body = body;
        this.email = email;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
    }

    public Document(DocumentDto userDto) {
        this.id = UUID.randomUUID().toString();
        this.name = userDto.getName();
        this.body = userDto.getBody();
        this.email = userDto.getEmail();
        this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        this.createdAt = new Date();
    }

    public Document(String email2, String passwordHash2, List<SimpleGrantedAuthority> authorities) {
        //TODO Auto-generated constructor stub
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(DocumentDto userDto) {
        this.name = userDto.getName();
        this.body = userDto.getBody();
        this.email = userDto.getEmail();
        if (!userDto.getPassword().isEmpty()) {
            this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        }
    }

    

    public String getPasswordHash() {
        return passwordHash;
    }


    public List<RoleEnum> getRoles() {
        return roles;
    }


    public void setRoles(List<RoleEnum> roles) {
        this.roles = roles;
    }

    

}
