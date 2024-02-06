package com.example.authservice.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {
    private final String secret;
    private final long expiration;
    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.secret=secret;
        this.expiration=expiration;
    }
    public String createToken(UserDetails userDetails) {
        System.out.println(userDetails);
        Map<String, Object> claims = new HashMap<>();
        String authorities = mapAuthorityToGrantedAuthorityString(userDetails.getAuthorities());
        claims.put("authorities", authorities);
        String username = userDetails.getUsername();
        return generateToken(claims, username);
    }
    public String extractAuthorities(String token) {
        Function<Claims, String> extractFunc = claims -> {
            return (String) claims.get("authorities");
        };
        return extractClaim(token, extractFunc);
    }
    public String extractUsername(String token) {
        if (token != null) return extractClaim(token, Claims::getSubject);
        return null;
    }
    private <T> T extractClaim(String token, Function<Claims, T> extractFunc) {
        Claims claims = extractAllClaims(token);
        return extractFunc.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        if (token!=null) return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return null;
    }
    public String mapAuthorityToGrantedAuthorityString(Collection<? extends GrantedAuthority> authorityList) {
        return authorityList.stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    }
    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
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
    private String generateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(calcExpirationDateFromNow())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    private Date calcExpirationDateFromNow() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }
}
