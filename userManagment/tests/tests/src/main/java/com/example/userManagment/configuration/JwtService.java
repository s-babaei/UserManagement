package com.example.userManagment.configuration;

import com.example.userManagment.model.UserStatus;
import com.example.userManagment.model.entity.User;
import com.example.userManagment.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtService {

    @Autowired
    UserRepository userRepository;
    public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
        public String generateToken(Authentication authentication,String userName) {

            Optional<User> user = userRepository.findByUsername(userName);
            if(user.get().getUserStatus().equals(UserStatus.NOTACTIVE)){
                 return "User is not active";
            }
            Map<String, Object> claims = new HashMap<>();

            claims.put("scopes",authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")));
            return createToken(claims, userName);
        }

        private String createToken(Map<String, Object> claims, String userName) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userName)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                    .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        }

        private Key getSignKey() {
            byte[] keyBytes= Decoders.BASE64.decode(SECRET);
            return Keys.hmacShaKeyFor(keyBytes);
        }

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
            return Jwts
                    .parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }

        private Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public Boolean validateToken(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }


}