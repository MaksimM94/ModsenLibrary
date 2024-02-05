package com.example.authservice.security.jwt.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtValidityResponse {
    private String token;
    private TokenStatus tokenStatus;
}
