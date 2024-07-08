package uz.pdp.appfastfood.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtProvider {
    @Value("${app.jwt.expireDate}")
    private Integer days;
    @Value("${app.jwt.secretKey}")
    private String secretKey;

    @Cacheable(key = "#username",value = "token.username")
    public String generateToken(String username) {
        Date date = new Date(System.currentTimeMillis() + days * 24 * 60 * 60 * 1000);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(date)
                .signWith(getKey())
                .compact();
    }

    @Cacheable(key = "#token", value = "token.subject")
    public String getSubject(String token) {
        try {
            return ((Claims) Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parse(token)
                    .getPayload()).getSubject();
        }  catch (MalformedJwtException | IllegalArgumentException | SecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private SecretKey getKey() {
        byte[] decode = Base64.getDecoder().decode(secretKey);
        return Keys.hmacShaKeyFor(decode);
    }
}
