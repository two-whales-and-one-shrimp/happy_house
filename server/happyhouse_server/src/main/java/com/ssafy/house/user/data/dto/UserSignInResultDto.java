package com.ssafy.house.user.data.dto;

public class UserSignInResultDto {
  private String userId;
  private String accessToken;
  private String refreshToken;
  private boolean isAdmin;

  public UserSignInResultDto() {
  }
  
  public UserSignInResultDto(String userId, String userPassword, String accessToken, String refreshToken,
      boolean isAdmin) {
    this.userId = userId;
    this.accessToken = accessToken;
    this.refreshToken = refreshToken;
    this.isAdmin = isAdmin;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
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

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }
}
