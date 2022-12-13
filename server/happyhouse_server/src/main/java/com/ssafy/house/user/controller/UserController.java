package com.ssafy.house.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @PostMapping("/signup")
  public boolean signUp(@RequestBody Map<String, String> userInfo) {
    if (userService.signUp(userInfo.get("id"), userInfo.get("password"), userInfo.get("email"))) {
      return true;
    }
    return false;
  }

  @PostMapping("/signin")
  public ResponseEntity<UserSignInResultDto> signIn(@RequestBody Map<String, String> userInfo) {
    UserSignInResultDto userSignInResultDto = userService.signIn(userInfo.get("id"), userInfo.get("password"));
    if (userSignInResultDto != null) {
      return new ResponseEntity<>(userSignInResultDto, HttpStatus.OK);
    }
    return new ResponseEntity<>(userSignInResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @GetMapping("/{userId}")
  public boolean checkId(@PathVariable String userId) {
    if (userService.checkId(userId) == 0) {
      return true;
    }
    return false;
  }

  @PostMapping("/email")
  public void checkEmail(@RequestBody String userEamil) throws Exception {
    userService.checkEmail(userEamil);
  }

  @PostMapping("/code")
  public boolean checkCode(@RequestBody String userCode) {
    if (userService.checkCode(userCode)) {
      return true;
    }
    return false;
  }
  
  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return new ResponseEntity<String>("test", HttpStatus.OK);
  }
}