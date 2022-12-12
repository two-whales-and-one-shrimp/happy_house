package com.ssafy.house.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ssafy.house.user.data.repository.UserRepository;

@Service
public class UserDetailsImpl implements UserDetailsService{

  private final UserRepository userRepository;

  @Autowired
  public UserDetailsImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.getByUserId(username);
  }
  
}
