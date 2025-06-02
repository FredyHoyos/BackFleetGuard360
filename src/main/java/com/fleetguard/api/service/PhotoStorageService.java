package com.fleetguard.api.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PhotoStorageService {
    @Value("${file.upload-dir}")
    private String uploadDir;

    public String savePhoto(MultipartFile file, Long id, String username) throws IOException {
        String fileName = username + "_" + id + "_" + file.getOriginalFilename();

        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        Path filePath = path.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }

}
