package com.ssafy.house.user.service;

import com.ssafy.house.user.data.dto.UserSignInResultDto;

public interface UserService {
  boolean signUp(String userId, String userPassword, String userEmail) throws RuntimeException;

  UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException;

  long checkId(String userId) throws RuntimeException;

  void checkEmail(String userEmail) throws Exception;
  
  boolean checkCode(String userCode);
}
