package com.ssafy.house.user.data.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {
  @Id
  @Column(nullable = false, name = "id")
  private String userId;
  @Column(nullable = false, name = "password")
  private String userPassword;
  @Column(nullable = false, name = "email")
  private String userEmail;
  @Column(name = "token")
  private String refreshToken;

  @Column(name = "is_admin")
  private Boolean isAdmin = false;

  public String getUserId() {
    return userId;
  }
  public String getUserPassword() {
    return userPassword;
  }
  public String getUserEmail() {
    return userEmail;
  }
  public String getRefreshToken() {
    return refreshToken;
  }
  public Boolean isAdmin() {
    return isAdmin;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }
  public void setAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<String> roles = new ArrayList<>();
    if (isAdmin()) {
      roles.add("admin");
    } else {
      roles.add("user");
    }
    return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
  }
  @Override
  public String getPassword() {
    return getUserPassword();
  }
  @Override
  public String getUsername() {
    return getUserId();
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  @Override
  public boolean isEnabled() {
    return true;
  }
}
