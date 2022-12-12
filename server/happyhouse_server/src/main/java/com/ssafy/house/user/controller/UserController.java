package com.ssafy.house.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.user.data.dto.UserSignInResultDto;
import com.ssafy.house.user.service.UserService;

@RestController()
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/signin")
  public UserSignInResultDto signIn(@RequestBody Map<String, String> userInfo) {
    UserSignInResultDto userSignInResultDto = userService.signIn(userInfo.get("id"), userInfo.get("password"));
    if (userSignInResultDto != null) {
      return userSignInResultDto;
    }
    return null;
  }
}