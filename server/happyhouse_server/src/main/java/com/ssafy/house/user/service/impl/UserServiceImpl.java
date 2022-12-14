package com.ssafy.house.user.service.impl;


import java.util.Random;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.house.config.security.JWTProvider;
import com.ssafy.house.user.dao.UserDAO;
import com.ssafy.house.user.data.dto.UserListDto;
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
  @Autowired
  JavaMailSender mailSender;

  @Override
  public UserSignInResultDto signIn(String userId, String userPassword) throws RuntimeException {
    User user = userDAO.selectUserByIdAndPassword(userId, userPassword);
    if (user != null) {
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

  @Override
  public void checkEmail(String userEmail) throws Exception {
    MimeMessage message = createMessage(userEmail);
    mailSender.send(message);
  }
  
  @Override
  public boolean checkCode(String userCode) {
    if (code.equals(userCode)) {
      return true;
    }
    return false;
  }

  private MimeMessage createMessage(String userEmail) throws Exception {
    MimeMessage message = mailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, userEmail);//보내는 대상
    message.setSubject("HappyHouse 회원가입 이메일 인증");//제목

    String msgg = "";
    msgg += "<div style='margin:100px;'>";
    msgg += "<h1> 안녕하세요 HappyHouse입니다. </h1>";
    msgg += "<br>";
    msgg += "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
    msgg += "<br>";
    msgg += "<p>감사합니다!<p>";
    msgg += "<br>";
    msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
    msgg += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
    msgg += "<div style='font-size:130%'>";
    msgg += "CODE : <strong>";
    msgg += code + "</strong><div><br/> ";
    msgg += "</div>";
    message.setText(msgg, "utf-8", "html");//내용

    return message;
  }

  public static final String code = createKey();
    public static String createKey() {
    StringBuffer key = new StringBuffer();
    Random random = new Random();

    for (int i = 0; i < 8; i++) { // 인증코드 8자리
      int index = random.nextInt(2);

      switch (index) {
        case 0:
          key.append((char) ((int) (random.nextInt(26)) + 97));
          //  a~z  (ex. 1+97=98 => (char)98 = 'b')
          break;
        case 1:
          key.append((random.nextInt(10)));
          // 0~9
          break;
      }
    }

    return key.toString();
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
  
}
