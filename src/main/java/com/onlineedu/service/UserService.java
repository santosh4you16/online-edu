package com.onlineedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.onlineedu.model.UserModel;
import com.onlineedu.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRep;

    @Override
    public UserModel registeruser(UserModel user) {
        UserModel savedUser = null;
        try {
            savedUser = userRep.save(user);
            if (savedUser == null) {
                throw new Exception("User not saved");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedUser;
    }

}
