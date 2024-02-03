package com.example.authservice.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtRequest {
    private String username;
    private String password;
}
