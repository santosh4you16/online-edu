package com.onlineedu.service;

import com.onlineedu.model.Question;
import com.onlineedu.model.TestType;
import com.onlineedu.model.UserModel;

public interface IUserService {

    public UserModel registeruser(UserModel user);
    
    public boolean saveQuestion(Question question);
    
    public boolean saveTestType(TestType testType);
    
}
