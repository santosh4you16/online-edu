package com.onlineedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.onlineedu.model.UserModel;
import com.onlineedu.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRespository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userRespository.findByUsername(username);
    CustomUserDetails userDetail = null;
    if (user != null) {
      userDetail = new CustomUserDetails();
      userDetail.setUserModel(user);
    } else {
      throw new UsernameNotFoundException("User not exist with this username");
    }
    return userDetail;
  }
}
