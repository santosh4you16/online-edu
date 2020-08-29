package com.onlineedu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineedu.model.Question;
import com.onlineedu.model.TestType;
import com.onlineedu.model.UserModel;
import com.onlineedu.repository.QuestionRepository;
import com.onlineedu.repository.TestTypeRepository;
import com.onlineedu.repository.UserRepository;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private TestTypeRepository testTypeRepository;

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

    @Override
    public boolean saveQuestion(Question question) {
        try {
            questionRepository.saveAndFlush(question);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveTestType(TestType testType) {
        try {
            testTypeRepository.saveAndFlush(testType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
