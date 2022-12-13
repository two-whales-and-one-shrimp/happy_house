package com.ssafy.house.config.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTProvider {
  
  @Autowired
  private UserDetailsService userDetailsService;

  @Value("{springboot.jwt.secret}")
  private String secretKey = "secretKey";
  
  @PostConstruct
  public void init() {
    secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
  }

  public String createAccessToken(String userId, boolean isAdmin) {
    Claims claims = Jwts.claims().setSubject(userId);
    claims.put("role", isAdmin ? "admin" : "user");

    String token = Jwts.builder()
        .setClaims(claims)
        .setExpiration(new Date(System.currentTimeMillis() + 1800000))
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    return token;
  }

  public String createRefreshToken(String userId) {
    Calendar expireDate = Calendar.getInstance();
    expireDate.add(Calendar.DAY_OF_MONTH, 14);
    Claims claims = Jwts.claims().setSubject(userId);

    String token = Jwts.builder()
        .setClaims(claims)
        .setExpiration(expireDate.getTime())
        .signWith(SignatureAlgorithm.HS256, secretKey)
        .compact();

    return token;
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
    
    return new UsernamePasswordAuthenticationToken(userDetails
        , ""
        , userDetails.getAuthorities());
    
  }

  private String getUserId(String token) {
    return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
  }

  /*
   * http 헤더에서 토큰 가져오기
   */
  public String resolveToken(HttpServletRequest request) {
    return request.getHeader("X-ACCESS-TOKEN");
  }

  public boolean validateToken(String token) {
    try {
      Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return !claims.getBody().getExpiration().before(new Date());
    } catch (JwtException e) {
      return false;
    }
  }
}
