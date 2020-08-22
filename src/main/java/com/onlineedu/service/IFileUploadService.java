package com.onlineedu.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    public boolean uploadFile(MultipartFile file, String filePath, String username);

    public List<File> getUserFiles(String filePath, String username);
}
