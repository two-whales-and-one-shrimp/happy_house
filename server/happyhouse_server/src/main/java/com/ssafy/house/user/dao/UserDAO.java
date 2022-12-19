package com.ssafy.house.user.dao;

import java.util.List;

import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.entity.User;


public interface UserDAO {
  User insertUser(User user);

  User selectUserById(String userId);

  Long isSameId(String userId);
  
  void updateUserRefreshTokenById(String userId, String refreshToken);

  List<UserListDto> findAll();

  void deleteUserById(String userId) throws Exception;

  boolean upgradeUser(String userId) throws Exception;
}
