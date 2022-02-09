package raf.edu.rs.projekat.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "MY JWT SECRET";

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public String generateToken(String username,
                                boolean can_create_users,
                                boolean can_read_users,
                                boolean can_update_users,
                                boolean can_delete_users,
                                boolean can_search_machines,
                                boolean can_start_machines,
                                boolean can_stop_machines,
                                boolean can_restart_machines,
                                boolean can_create_machines,
                                boolean can_destroy_machines) {

        Map<String, Object> claims = new HashMap<>();
        // user permissions
        claims.put("can_create_users", can_create_users);
        claims.put("can_read_users", can_read_users);
        claims.put("can_update_users", can_update_users);
        claims.put("can_delete_users", can_delete_users);
        // machine permissions
        claims.put("can_search_machines", can_search_machines);
        claims.put("can_start_machines", can_start_machines);
        claims.put("can_stop_machines", can_stop_machines);
        claims.put("can_restart_machines", can_restart_machines);
        claims.put("can_create_machines", can_create_machines);
        claims.put("can_destroy_machines", can_destroy_machines);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    public boolean validateToken(String token, UserDetails user) {
        return (user.getUsername().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}
