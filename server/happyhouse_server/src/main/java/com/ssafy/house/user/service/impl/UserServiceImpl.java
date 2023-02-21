package com.ssafy.house.user.service.impl;


import java.util.Random;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.house.config.security.JWTProvider;
import com.ssafy.house.user.dao.UserDAO;
import com.ssafy.house.user.data.dto.UserDto;
import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.data.dto.UserSignInResultDto;
import com.ssafy.house.user.data.entity.User;
import com.ssafy.house.user.service.UserService;

import io.jsonwebtoken.JwtException;

@Service
public class UserServiceImpl implements UserService{

  @Autowired
  private UserDAO userDAO;
  @Autowired
  private JWTProvider jwtProvider;
  @Autowired
  private EmailUtil emailUtil;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  JavaMailSender mailSender;

  @Override
  public UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException {
    User user = userDAO.selectUserById(userId);
    if (user != null && passwordEncoder.matches(userPassword, user.getPassword())) {
      UserSignInResultDto userSignInResultDto = new UserSignInResultDto();
      userSignInResultDto.setUserId(userId);
      userSignInResultDto.setAccessToken(jwtProvider.createAccessToken(userId, user.isAdmin()));
      userSignInResultDto.setRefreshToken(jwtProvider.createRefreshToken(userId));
      userSignInResultDto.setAdmin(user.isAdmin());
      // DB에 refreshToken 저장
      userDAO.updateUserRefreshTokenById(userId, userSignInResultDto.getRefreshToken());
      return userSignInResultDto;
    } else {
      return null;
    }

  }

  @Override
  public boolean signOut(String userId, String token) {
    if (jwtProvider.getUserId(token).equals(userId)) {
      userDAO.updateUserRefreshTokenById(userId, null);
      return true;
    }

    return false;
  }

  @Override
  public boolean signUp(UserDto userDto) throws RuntimeException {
    User user = new User();
    user.setUserId(userDto.getUserId());
    user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
    user.setUserEmail(userDto.getUserEmail());
    
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

  
  @Override
  public void checkEmail(String userEmail) throws Exception {
    Random random = new Random();
    String code = String.valueOf(random.nextInt(888888) + 111111);

    emailUtil.checkEmailMessage(userEmail, code);
  }
  
  @Override
  public String checkCode(String userCode) {
    return emailUtil.getCode(userCode);
  }

  public List<UserListDto> findAll() {
    return userDAO.findAll();
  }

  @Override
  public void deleteUserById(String userId) throws Exception{
    userDAO.deleteUserById(userId);
  }

  @Override
  public boolean upgradeUser(String userId) throws Exception {
    return userDAO.upgradeUser(userId);
  }

  @Override
  public boolean findPassword(String userId) throws Exception {
    User user = userDAO.selectUserById(userId);
    if (user == null) {
      return false;
    }
    String userEmail = user.getUserEmail();
    
    Random random = new Random();
    StringBuffer key = new StringBuffer();
    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(2);

      switch (index) {
        case 0:
          key.append((char) ((int) (random.nextInt(26)) + 97));
          break;
        case 1:
          key.append((random.nextInt(10)));
          break;
      }
    }

    String tempPassword = key.toString();
    //임시 비밀번호 전송
    emailUtil.findPasswordMessage(userEmail, tempPassword);
    //임시 비밀번호 저장
    userDAO.updateUserPasswordById(userId, passwordEncoder.encode(tempPassword));
    return true;
  }

  @Override
  public String checkRefreshToken(String refreshToken) {
    try{
      Optional<User> user = userDAO.selectRefreshTokenByUserId(jwtProvider.getUserId(refreshToken));
      if (user.get().getRefreshToken().equals(refreshToken)) {
        String accessToken = jwtProvider.createAccessToken(user.get().getUserId(), user.get().isAdmin());
        return accessToken;
      } else {
        return null;
      }
    } catch (JwtException e) {
      return null;
    }
  }

  @Override
  public void updateUserPassword(UserDto userDto) throws Exception {
    userDAO.updateUserPasswordById(userDto.getUserId(), passwordEncoder.encode(userDto.getUserPassword()));
  }

  @Override
  public void updateUserEmail(UserDto userDto) throws Exception {
    userDAO.updateUserEmailById(userDto.getUserId(), userDto.getUserEmail());
  }

  @Override
  public UserDto getUserInfo(String userId) {
    User user = userDAO.selectUserById(userId);
    if (user != null) {
      UserDto userDto = new UserDto();
      userDto.setUserId(userId);
      userDto.setUserPassword(user.getPassword());
      userDto.setUserEmail(user.getUserEmail());
      return userDto;
    } else {
      return null;
    }
  }
  
}
