package com.onlineedu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.onlineedu.model.UserModel;
import com.onlineedu.service.IUserService;

@Controller
public class UserController {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;
  
  @Autowired
  private IUserService userService;
  
  @PostMapping("/register.do")
  public void registerUser(@RequestBody UserModel user) {
    try {
      if(user.getUsername() == null || user.getLastName() == null ) {
        throw new Exception("username or password cant be null");
      }
      // encrypting the password
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      userService.registeruser(user);
    }catch( Exception e ) {
      e.printStackTrace();
    }
  }
}
