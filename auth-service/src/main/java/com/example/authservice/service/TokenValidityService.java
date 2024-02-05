package com.example.authservice.service;

import com.example.authservice.security.jwt.JwtProvider;
import com.example.authservice.security.jwt.dto.JwtValidityRequest;
import com.example.authservice.security.jwt.dto.JwtValidityResponse;
import com.example.authservice.security.jwt.dto.TokenStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenValidityService {
    private JwtProvider jwtProvider;
    public JwtValidityResponse validateToken(JwtValidityRequest jwtValidityRequest) {
        String token = jwtValidityRequest.getToken();
        if (token == null)
            return null;
        boolean validity = jwtProvider.validateToken(token);
        TokenStatus tokenStatus = validity ? TokenStatus.VALID : TokenStatus.EXPIRED;
        return JwtValidityResponse.builder()
                .token(token)
                .tokenStatus(tokenStatus)
                .build();
    }
}
