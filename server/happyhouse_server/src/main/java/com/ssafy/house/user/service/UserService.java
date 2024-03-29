package com.ssafy.house.user.service;

import java.util.List;

import com.ssafy.house.user.data.dto.UserDto;
import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.dto.UserSignInResultDto;

public interface UserService {
  boolean signUp(UserDto userDto) throws RuntimeException;

  UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException;

  boolean signOut(String userId, String token);

  long checkId(String userId) throws RuntimeException;

  void checkEmail(String userEmail) throws Exception;
  
  String checkCode(String userCode);

  List<UserListDto> findAll();

  void deleteUserById(String userId) throws Exception;

  boolean upgradeUser(String userId) throws Exception;
  
  boolean findPassword(String userId) throws Exception;

  void updateUserPassword(UserDto userDto) throws Exception;

  void updateUserEmail(UserDto userDto) throws Exception;

  String checkRefreshToken(String refreshToken);

  UserDto getUserInfo(String userId);

}
