package com.ssafy.house.user.data.dto;

public class UserListDto {
  private String userId;
  private boolean isAdmin;

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }
  public boolean isAdmin() {
    return isAdmin;
  }
  public void setAdmin(boolean isAdmin) {
    this.isAdmin = isAdmin;
  }  
}
