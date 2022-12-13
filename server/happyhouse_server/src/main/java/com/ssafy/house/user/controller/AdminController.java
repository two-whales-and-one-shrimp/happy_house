package com.ssafy.house.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.house.user.data.dto.UserListDto;
import com.ssafy.house.user.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
  private final UserService userService;

  @Autowired
  public AdminController(UserService userService){
    this.userService = userService;
  }

  @GetMapping
  public List<UserListDto> getUserList(){
    return userService.findAll();
  }
}
