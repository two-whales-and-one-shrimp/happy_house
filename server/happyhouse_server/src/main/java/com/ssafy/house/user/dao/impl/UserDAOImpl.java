package com.ssafy.house.user.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.house.user.dao.UserDAO;
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
}
