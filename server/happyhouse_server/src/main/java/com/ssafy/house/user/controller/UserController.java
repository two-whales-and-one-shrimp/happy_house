package com.ssafy.house.user.controller;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.user.data.dto.UserDto;
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
  public ResponseEntity<?> signUp(@RequestBody UserDto userDto) {
    if (userService.signUp(userDto)) {
      return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PostMapping("/signin")
  public ResponseEntity<UserSignInResultDto> signIn(@RequestBody Map<String, String> userInfo) {
    UserSignInResultDto userSignInResultDto = userService.signIn(userInfo.get("id"), userInfo.get("password"));
    if (userSignInResultDto != null) {
      return new ResponseEntity<>(userSignInResultDto, HttpStatus.OK);
    }
    return new ResponseEntity<>(userSignInResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @GetMapping("/signout/{userId}")
  public ResponseEntity<?> signOut(@RequestHeader("X-ACCESS-TOKEN") String token, @PathVariable String userId) {
    if (userService.signOut(userId, token))
      return new ResponseEntity<>(HttpStatus.OK);
    else 
      return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable String userId) throws Exception {
    userService.deleteUserById(userId);
    return new ResponseEntity<String>("success", HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<?> checkId(@PathVariable String userId) {
    if (userService.checkId(userId) == 0) {
      return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @PostMapping("/email")
  public ResponseEntity<?> checkEmail(@RequestBody String userEamil) throws Exception {
    userService.checkEmail(userEamil);
    return new ResponseEntity<String>("success", HttpStatus.OK);
  }

  @PostMapping("/code")
  public ResponseEntity<?> checkCode(@RequestBody String userCode) {
    if (userService.checkCode(userCode)) {
      return new ResponseEntity<String>("success", HttpStatus.OK);
    }
    return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
  }
  
  @GetMapping("/test")
  public ResponseEntity<String> test(HttpServletResponse response) {
    // if (response.getStatus() == 401) {
    //   return new ResponseEntity<String>("만료", HttpStatus.UNAUTHORIZED);
    // }
    return new ResponseEntity<String>("test", HttpStatus.OK);
  }

  @GetMapping("/refresh")
  public ResponseEntity<String> refresh(@RequestHeader("X-REFRESH-TOKEN") String refreshToken) {
    System.out.println("refresh");
    String accessToken = userService.checkRefreshToken(refreshToken);
    if (accessToken != null) {
      return new ResponseEntity<String>(accessToken, HttpStatus.OK);  
    } else {
      return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}