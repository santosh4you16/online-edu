package com.onlineedu.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineedu.model.Question;
import com.onlineedu.service.ITestService;

@RestController
public class TestController extends AbstractController {

    @Autowired
    ITestService testService;

    @GetMapping("/getTest")
    public void getTest(@RequestParam("sub") int subject, HttpServletResponse response) {
        try {
            if (subject <= 0) {
                generateJsonError(response, "Please select subject to get the questions");
            }
            List<Question> queList = testService.getTest(subject);
            generateJson(response, queList);
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, "Something went wrong");
        }
    }

}
