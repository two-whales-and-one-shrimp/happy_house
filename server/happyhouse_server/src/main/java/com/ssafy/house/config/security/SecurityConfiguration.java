package com.ssafy.house.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfiguration{
  private final JWTProvider jwtProvider;

  @Autowired
  public SecurityConfiguration(JWTProvider jwtProvider) {
    this.jwtProvider = jwtProvider;
  }
  
  @Bean
  // @Order(SecurityProperties.BASIC_AUTH_ORDER)
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
        .csrf().disable()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll() // 브라우저가 보낸 preflight 요청 해결
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/user/signin", "/user/signup", "/user/{userId}", "/user/email", "/user/code", "/user/refresh", "/user/findpassword", "/user/update/*")
        .permitAll()
        .antMatchers("/admin/**").hasRole("admin")
        .antMatchers("/user/signout/{userId}").hasAnyRole("admin", "user")
        .and()
        .addFilterBefore(new JWTAuthenticatioFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(new JWTExceptionFilter(), JWTAuthenticatioFilter.class);

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
      return new BCryptPasswordEncoder();
  }
}
