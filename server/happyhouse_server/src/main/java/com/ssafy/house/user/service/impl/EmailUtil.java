package com.ssafy.house.user.service.impl;

import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailUtil {

  @Autowired
  JavaMailSender mailSender;
  @Autowired
  RedisUtil redisUtil;

  public static String code;
  public static String tempPassword;

  public void findPasswordMessage(String userEmail, String tempPassword) throws Exception {
    MimeMessage message = mailSender.createMimeMessage();
    message.addRecipients(RecipientType.TO, userEmail);//보내는 대상
    message.setSubject("HappyHouse 비밀번호 찾기");//제목

    String msgg = "";
    msgg += "<div style='margin:100px;'>";
    msgg += "<h1> 안녕하세요 HappyHouse입니다. </h1>";
    msgg += "<br>";
    msgg += "<p>아래 비밀번호로 로그인 후 비밀번호를 수정해 주세요<p>";
    msgg += "<br>";
    msgg += "<p>감사합니다!<p>";
    msgg += "<br>";
    msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
    msgg += "<h3 style='color:blue;'>임시 비밀번호 입니다.</h3>";
    msgg += "<div style='font-size:130%'>";
    msgg += "CODE : <strong>";
    msgg += tempPassword + "</strong><div><br/> ";
    msgg += "</div>";
    message.setText(msgg, "utf-8", "html");//내용

    mailSender.send(message);
  }

  public void checkEmailMessage(String userEmail, String code) throws Exception {
    redisUtil.setDataExpire(code, userEmail, 60 * 5L);
    
    MimeMessage message = mailSender.createMimeMessage();

    message.addRecipients(RecipientType.TO, userEmail);//보내는 대상
    message.setSubject("HappyHouse 이메일 인증");//제목

    String msgg = "";
    msgg += "<div style='margin:100px;'>";
    msgg += "<h1> 안녕하세요 HappyHouse입니다. </h1>";
    msgg += "<br>";
    msgg += "<p>아래 코드를 입력해주세요<p>";
    msgg += "<br>";
    msgg += "<p>감사합니다!<p>";
    msgg += "<br>";
    msgg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
    msgg += "<h3 style='color:blue;'>이메일 인증 코드입니다.</h3>";
    msgg += "<div style='font-size:130%'>";
    msgg += "CODE : <strong>";
    msgg += code + "</strong><div><br/> ";
    msgg += "</div>";
    message.setText(msgg, "utf-8", "html");//내용

    mailSender.send(message);
  }

  public String getCode(String userEmail) {
    return redisUtil.getData(userEmail).toString();
  }

}
