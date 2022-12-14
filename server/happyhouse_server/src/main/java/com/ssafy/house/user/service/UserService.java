package com.ssafy.house.user.service;

import java.util.List;

import com.ssafy.house.user.data.dto.UserDto;
import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.dto.UserSignInResultDto;

public interface UserService {
  boolean signUp(UserDto userDto) throws RuntimeException;

  UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException;

  long checkId(String userId) throws RuntimeException;

  void checkEmail(String userEmail) throws Exception;
  
  boolean checkCode(String userCode);

  List<UserListDto> findAll();

}
