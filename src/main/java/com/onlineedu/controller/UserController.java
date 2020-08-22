package com.onlineedu.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.onlineedu.model.RoleModel;
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
        SecurityContext ctx=(SecurityContext)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        Authentication auth=ctx.getAuthentication();
        try {
            if (file.isEmpty()) {
                throw new Exception("File is Empty");
            }
            
            boolean isUploaded = fileUploadService.uploadFile(file, FILE_PATH, auth.getName());
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
}
