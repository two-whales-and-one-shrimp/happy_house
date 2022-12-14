package com.ssafy.house.user.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
  public User insertUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User selectUserByIdAndPassword(String userId, String userPassword) {
    return userRepository.findByUserIdAndUserPassword(userId, userPassword);
  }

  @Override
  public Long isSameId(String userId) {
    return userRepository.countByUserId(userId);
  }

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

  @Override
  public void deleteUserById(String userId) throws Exception{
    userRepository.deleteById(userId);
  }

  @Override
  public boolean upgradeUser(String userId) throws Exception {
    Optional<User> user = userRepository.findById(userId);
    if(user.isPresent()){
      User dto = user.get();
      dto.setAdmin(true);
      userRepository.save(dto);
      return true;
    }
    return false;
  }
}
