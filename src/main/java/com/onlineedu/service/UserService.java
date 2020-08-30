package com.onlineedu.service;

import java.util.ArrayList;
import java.util.List;

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
            TestType ttype = testTypeRepository.findById(question.getTestTypeId()).get();
            question.setTestType(ttype);
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

    @Override
    public List<TestType> getTestType() {
        List<TestType> testTypeList = new ArrayList<>();
        try {
            testTypeList = testTypeRepository.findAll();
            if (testTypeList.isEmpty() || testTypeList == null) {
                throw new Exception("No record Found");
            }
            return testTypeList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
