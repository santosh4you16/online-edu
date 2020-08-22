package com.onlineedu.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileUploadService {

    public boolean uploadFile(MultipartFile file, String filePath, String username);
}
