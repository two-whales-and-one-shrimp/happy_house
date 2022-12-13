package com.ssafy.house.user.dao;

import java.util.List;

import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.entity.User;


public interface UserDAO {
  User selectUserByIdAndPassword(String userId, String userPassword);

  void updateUserRefreshTokenById(String userId, String refreshToken);

  List<UserListDto> findAll();
}
