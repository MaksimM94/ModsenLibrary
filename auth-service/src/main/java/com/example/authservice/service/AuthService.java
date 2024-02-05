package com.example.authservice.service;

import com.example.authservice.security.jwt.JwtProvider;
import com.example.authservice.security.jwt.dto.JwtRequest;
import com.example.authservice.security.jwt.dto.JwtResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {
    private JwtProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    public JwtResponse createToken(JwtRequest jwtRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        String token = jwtProvider.createToken((UserDetails) authentication.getPrincipal());
        return new JwtResponse(token);

    }
}
