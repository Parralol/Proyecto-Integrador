package com.ieti.health.config.security;


import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ieti.health.repository.User.Document;
import com.ieti.health.service.DocumentService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final DocumentService userRepository;

    public JwtUserDetailsService(DocumentService userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Document> optionalUser = userRepository.findByEmail(username);
        if (optionalUser.isPresent()) {
            Document userEntity = optionalUser.get();
            List<SimpleGrantedAuthority> authorities = userEntity.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .toList();
            return new User(userEntity.getEmail(), userEntity.getPasswordHash(), authorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
