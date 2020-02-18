package com.jithin.ecommerce.configuration;

import com.jithin.ecommerce.model.User;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.jithin.ecommerce.configuration.SecurityConfig.EXPIRATION_TIME;
import static com.jithin.ecommerce.configuration.SecurityConfig.SECRET;
@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        String userid = user.getId();
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userid);
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setSubject(userid)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public boolean valid_token(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("invalid signature");
        } catch (MalformedJwtException e) {
            System.out.println("invalid jwt token");
        } catch (ExpiredJwtException e) {
            System.out.println("token expired");
        } catch (UnsupportedJwtException ex) {
            System.out.println("unsupported jwt exception");
        } catch (IllegalArgumentException ex) {
            System.out.println("illegal argument");
        }
        return false;
    }

    public String getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();

        String userid = (String) claims.get("id");
        return  userid;

    }














}
