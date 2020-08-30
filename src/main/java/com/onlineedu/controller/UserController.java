package com.onlineedu.controller;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onlineedu.model.Question;
import com.onlineedu.model.RoleModel;
import com.onlineedu.model.TestType;
import com.onlineedu.model.UserModel;
import com.onlineedu.repository.RoleRepository;
import com.onlineedu.service.IFileUploadService;
import com.onlineedu.service.IUserService;

@Controller
public class UserController extends AbstractController {

    @Value("${upload.path}")
    public String FILE_PATH;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private IUserService userService;

    @Autowired
    private IFileUploadService fileUploadService;

    @PostMapping("/register.do")
    public void registerUser(@RequestBody UserModel user, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (user.getUsername() == null || user.getUsername().isEmpty() || user.getPassword() == null
                || user.getPassword().isEmpty()) {
                throw new Exception("username or password cant be null");
            }
            // encrypting the password
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

            // Getting role
            RoleModel roles = roleRepo.findById(user.getRoleId());
            Set<RoleModel> roleset = new HashSet<>();
            roleset.add(roles);
            user.setRoles(roleset);

            // saving user
            UserModel umodel = userService.registeruser(user);
            if (umodel == null) {
                throw new Exception("Registration failed!");
            }
            generateJson(response, "OK");
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e);
        }
    }

    @PostMapping("/uploadFile.do")
    public void uploadFile(@RequestParam("filename") MultipartFile file, HttpServletResponse response,
        HttpServletRequest request) {
        try {
            if (file.isEmpty()) {
                throw new Exception("File is Empty");
            }
            boolean isUploaded = fileUploadService.uploadFile(file, FILE_PATH, getUserContext(request).getName());
            if (isUploaded) {
                generateJson(response, "file SuccessFullyUploaded");
            } else {
                throw new Exception("Something went wrong");
            }

        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e.getMessage());
        }
    }

    @GetMapping("/getUserFiles.do")
    public void getUerFiles(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<File> fileList = fileUploadService.getUserFiles(FILE_PATH, getUserContext(request).getName());
            if (fileList.isEmpty() || fileList == null) {
                throw new Exception("No record found");
            }
            List<String> fileNames = fileList.stream().map(file -> file.getName()).collect(Collectors.toList());
            generateJson(response, fileNames);
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e.getMessage());
        }
    }

    @PostMapping("/saveQuestion.do")
    public void saveQuestion(@RequestBody Question question, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (question.getTestTypeId() <= 0) {
                throw new Exception("test type id cannot be zero");
            }
            boolean isSaved = userService.saveQuestion(question);
            if (!isSaved) {
                throw new Exception("Question not saved");
            }
            generateJson(response, "Question Saved Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e.getMessage());
        }
    }

    @PostMapping("/saveTestType.do")
    public void saveTestType(@RequestBody TestType testType, HttpServletRequest request, HttpServletResponse response) {
        try {
            boolean isSaved = userService.saveTestType(testType);
            if (!isSaved) {
                throw new Exception("Test type not saved XXX");
            }
            generateJson(response, "Test type saved Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e.getMessage());
        }
    }

    @GetMapping("/getTestType.do")
    public void getTaskType(HttpServletResponse response, HttpServletRequest request) {
        try {
            List<TestType> testTypeList = userService.getTestType();
            if (testTypeList == null) {
                throw new Exception("No record Found!");
            }
            generateJson(response, testTypeList);
        } catch (Exception e) {
            e.printStackTrace();
            generateJsonError(response, e.getMessage());
        }
    }
}
