package com.ieti.health.repository.User;

public class DocumentDto {
    private final String name;
    private final String body;
    private final String email;
    private final String password;

    public DocumentDto() {
        this.name = "";
        this.body = "";
        this.email = "";
        this.password = "";
    }

    public DocumentDto(String name, String body, String email, String password) {
        this.name = name;
        this.body = body;
        this.email = email;
        this.password = password;
    }

    public DocumentDto(String name, String body, String email) {
        this.name = name;
        this.body = body;
        this.email = email;
        this.password = "";
    }

    public String getName() {
        return name;
    }

    public String getBody() {
        return body;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
