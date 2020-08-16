package com.onlineedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.onlineedu.model.UserModel;

@Controller
public class UserController {

  @PostMapping("/register")
  public void registeruser(@RequestBody UserModel user) {
    
  }
}
