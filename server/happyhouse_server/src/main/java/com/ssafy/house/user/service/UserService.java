package com.ssafy.house.user.service;

import java.util.List;

import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.dto.UserSignInResultDto;

public interface UserService {
  UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException;
  List<UserListDto> findAll();
}
