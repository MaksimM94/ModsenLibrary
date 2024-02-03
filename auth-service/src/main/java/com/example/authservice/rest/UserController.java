package com.example.authservice.rest;

import com.example.authservice.model.entity.User;
import com.example.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private UserRepository userRepository;
    @GetMapping("/users")
    @PreAuthorize("hasAuthority('read') && hasAuthority('write')")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
