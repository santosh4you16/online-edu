package com.onlineedu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.onlineedu.model.UserModel;
import com.onlineedu.service.IUserService;

@Controller
public class UserController extends AbstractController{

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private IUserService userService;

  @PostMapping("/register.do")
  public void registerUser(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response) {
    try {
      if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
          || user.getPassword().isEmpty()) {
        throw new Exception("username or password cant be null");
      }
      // encrypting the password
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      UserModel umodel = userService.registeruser(user);
      if (umodel == null) {
        throw new Exception("Registration failed!");
      }
      generateJson(response, "OK");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
