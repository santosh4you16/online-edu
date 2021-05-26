package com.onlineedu.service;

import java.util.List;

import com.onlineedu.model.Question;

public interface ITestService {

    public List<Question> getTest(int testType);
}
