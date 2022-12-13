package com.ssafy.house.user.service;

import com.ssafy.house.user.data.dto.UserSignInResultDto;

public interface UserService {
  UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException;
}
