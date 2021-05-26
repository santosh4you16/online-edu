package com.onlineedu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineedu.model.Question;
import com.onlineedu.repository.QuestionRepository;

@Service
public class TestService implements ITestService {

    @Autowired
    QuestionRepository questRepo;
    
    @Override
    public List<Question> getTest(int testType) {
        try {
            List<Question> queList = questRepo.findByTestType(testType);
            return queList;
        }catch( Exception e ) {
            e.printStackTrace();
            return null;
        }
    }
}
