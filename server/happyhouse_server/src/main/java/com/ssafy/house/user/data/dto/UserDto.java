package com.ssafy.house.user.data.dto;

public class UserDto {

  private String userId;
  private String userPassword;
  private String userEmail;
  private String refreshToken;
  private boolean isAdmin;

  public UserDto() {
  }

  public UserDto(String userId, String userPassword, String userEmail, String refreshToken, boolean isAdmin) {
    this.userId = userId;
    this.userPassword = userPassword;
    this.userEmail = userEmail;
    this.refreshToken = refreshToken;
    this.isAdmin = isAdmin;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public boolean isAdmin() {
    return isAdmin;
  }

  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

}