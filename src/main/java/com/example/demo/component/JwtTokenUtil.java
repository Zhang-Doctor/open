package com.example.demo.component;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.expire}")
    private Long JWT_EXPIRE;

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("created", new Date());
        return generateToken(claims);
    }

    /**
     * 根据负载生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .signWith(secretKey, Jwts.SIG.HS256)
                .expiration(generateExpirationDate())
                .claims(claims)
                .compact();
    }

    /**
     * 从token中获取JWT的负载信息
     */
    private Jws<Claims> getClaimsFromToken(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor("12345678123456781234567812345678".getBytes());
        Jws<Claims> claimsJws = null;
        try {
            claimsJws = Jwts.parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token);
        } catch (Exception e) {
            log.error("JWT格式验证失败: {}", token);
        }
        return claimsJws;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + JWT_EXPIRE * 1000);
    }

    /**
     * 获取token的过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Jws<Claims> claims = getClaimsFromToken(token);
        return claims.getPayload().getExpiration();
    }
}
