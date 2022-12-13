package com.ssafy.house.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.house.config.security.JWTProvider;
import com.ssafy.house.user.dao.UserDAO;
import com.ssafy.house.user.data.dto.UserSignInResultDto;
import com.ssafy.house.user.data.entity.User;
import com.ssafy.house.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserDAO userDAO;
  @Autowired
  private JWTProvider jwtProvider;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException {
    User user = userDAO.selectUserByIdAndPassword(userId, userPassword);
    if (user != null) {
      UserSignInResultDto userSignInResultDto = new UserSignInResultDto();
      userSignInResultDto.setUserId(userId);
      userSignInResultDto.setAccessToken(jwtProvider.createAccessToken(userId, user.isAdmin()));
      userSignInResultDto.setRefreshToken(jwtProvider.createRefreshToken(userId));
      userSignInResultDto.setAdmin(user.isAdmin());
      return userSignInResultDto;
    } else {
      return null;
    }

  }

  @Override
  public boolean signUp(String userId, String userPassword, String userEmail) throws RuntimeException {
    User user = new User();
    user.setUserId(userId);
    user.setUserPassword(passwordEncoder.encode(userPassword));
    user.setUserEmail(userEmail);
    
    User savedUser = userDAO.insertUser(user);
    
    if (!savedUser.getUserId().isEmpty()) {
      return true;
    }
    return false;
  }

  @Override
  public long checkId(String userId) throws RuntimeException {
    return userDAO.isSameId(userId);
  }
}
