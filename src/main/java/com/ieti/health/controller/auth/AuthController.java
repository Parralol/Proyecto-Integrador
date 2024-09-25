package com.ieti.health.controller.auth;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerErrorException;

import com.ieti.health.config.security.JwtUtil;
import com.ieti.health.repository.User.Document;
import com.ieti.health.service.DocumentService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private DocumentService usersService;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {

        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {
        Optional<Document> xf = usersService.findByEmail(loginDto.getUsername());
        if(xf.isPresent() && BCrypt.checkpw(loginDto.getPassword(), xf.get().getPasswordHash())){
            return ResponseEntity.ok( jwtUtil.generateToken(loginDto.getUsername(), xf.get().getRoles()));
        }else{
            throw new ServerErrorException( null, null);
        }
        
    }


}
