package com.ssafy.house.user.dao;

import java.util.List;

import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.entity.User;


public interface UserDAO {
  User insertUser(User user);

  User selectUserByIdAndPassword(String userId, String userPassword);

  Long isSameId(String userId);
  
  void updateUserRefreshTokenById(String userId, String refreshToken);

  List<UserListDto> findAll();
}
