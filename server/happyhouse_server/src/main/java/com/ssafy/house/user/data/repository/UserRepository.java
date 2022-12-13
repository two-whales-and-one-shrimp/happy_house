package com.ssafy.house.user.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.house.user.data.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
  public User findByUserIdAndUserPassword(String userId, String userPassword);

  public User getByUserId(String userId);

  public Long countByUserId(String userId);
}
