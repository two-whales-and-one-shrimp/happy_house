package com.ssafy.house.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.house.user.dao.UserDAO;
import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.entity.User;
import com.ssafy.house.user.data.repository.UserRepository;

@Repository
public class UserDAOImpl implements UserDAO {

  private final UserRepository userRepository;

  @Autowired
  public UserDAOImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public User selectUserByIdAndPassword(String userId, String userPassword) {
    return userRepository.findByUserIdAndUserPassword(userId, userPassword);
  }

  @Override
  public void updateUserRefreshTokenById(String userId, String refreshToken) {
    User user = userRepository.getByUserId(userId);
    user.setRefreshToken(refreshToken);
    userRepository.save(user);
  }

  @Override
  public List<UserListDto> findAll() {
    List<User> list = userRepository.findAll();
    List<UserListDto> resultList = new ArrayList<>();

    for(User user : list){
      UserListDto u = new UserListDto();
      u.setUserId(user.getUserId());
      u.setAdmin(user.isAdmin());
      resultList.add(u);
    }
    return resultList;
  }
}
