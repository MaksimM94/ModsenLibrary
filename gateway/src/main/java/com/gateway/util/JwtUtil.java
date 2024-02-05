package com.gateway.util;

import com.gateway.dto.JwtValidityRequest;
import com.gateway.dto.JwtValidityResponse;
import com.gateway.dto.TokenStatus;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JwtUtil {
    private final String JWT_SECRET;
    public JwtUtil(@Value("${jwt.secret}") String secret) {
        JWT_SECRET = secret;
    }
    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
    public JwtValidityResponse validateToken(JwtValidityRequest jwtValidityRequest) {
        String token = jwtValidityRequest.getToken();
        if (token == null)
            return JwtValidityResponse
                    .builder()
                    .token(token)
                    .tokenStatus(TokenStatus.EXPIRED)
                    .build();
        boolean validity = validateToken(token);
        TokenStatus tokenStatus = validity ? TokenStatus.VALID : TokenStatus.EXPIRED;
        return JwtValidityResponse.builder()
                .token(token)
                .tokenStatus(tokenStatus)
                .build();
    }
    private boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (SignatureException sEx) {
            log.error("Invalid signature", sEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }
}
