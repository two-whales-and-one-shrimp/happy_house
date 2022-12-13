package com.ssafy.house.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
        .antMatchers("/", "/css/**", "/images/**", "/js/**", "/user/signin", "/user/test").permitAll()
        .antMatchers("admin/**").hasAuthority("admin")
        .and()
        .addFilterBefore(new JWTAuthenticatioFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
