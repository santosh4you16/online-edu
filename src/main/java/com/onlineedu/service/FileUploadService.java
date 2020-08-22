package com.onlineedu.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService implements IFileUploadService {

    @Override
    public boolean uploadFile(MultipartFile file, String filePath, String username) {
        try {
            byte[] bytes = file.getBytes();
            String finalPath = getUserFilePath(filePath, username);
            if (finalPath == null) {
                throw new Exception("Unable to create directory");
            }
            Path path = Paths.get(finalPath + file.getOriginalFilename());
            Files.write(path, bytes);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getUserFilePath(String path, String username) {
        try {
            String finalPath = path + username + "/";
            File file = new File(finalPath);
            if (file.exists()) {
                return finalPath;
            }
            boolean isCreated = file.mkdirs();
            if (isCreated) {
                return finalPath;
            } else {
                throw new Exception("Unable to create file");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
