package com.ssafy.house.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;

public class JWTAuthenticatioFilter extends OncePerRequestFilter{
  private final JWTProvider jwtProvider;

  public JWTAuthenticatioFilter(JWTProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    String accessToken = jwtProvider.resolveAccessToken(request);
    String refreshToken = jwtProvider.resolveRefreshToken(request);
    System.out.println(refreshToken);
    if (accessToken != null) {
      try {
        jwtProvider.validateToken(accessToken);
        Authentication authentication = jwtProvider.getAuthentication(accessToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
      } catch (ExpiredJwtException e) {
        System.out.println("401");
        if (refreshToken == null) {
          throw new ExpiredJwtException(null, null, null);
        } else {
          try {
            jwtProvider.validateToken(refreshToken);
          } catch (JwtException je) {
            System.out.println("리프레시 실패");
            throw new JwtException(null);
          }
        }
      } catch (JwtException e) {
        System.out.println("500");
        throw new JwtException(null);
      }
    }
    
    filterChain.doFilter(request, response);
  }
  
}
