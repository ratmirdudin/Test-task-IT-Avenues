package com.ratmirdudin.userservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileStorageService {
    @Value("${fileStore.root}")
    private String root;

    public String saveFile(MultipartFile multipartFile) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        String uuid = UUID.randomUUID().toString();
        log.info("Saving uploaded file: {} in directory: {}", fileName, uuid);

        createDirectoryIfNotExists(Paths.get(root));

        Path uploadDirectory = Paths.get(root, uuid);
        createDirectoryIfNotExists(uploadDirectory);

        String fileExtension = getFileExtension(fileName);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirectory.resolve("file" + fileExtension);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new RuntimeException("Error saving uploaded file: " + fileName, ioe);
        }

        return uuid;
    }

    private boolean createDirectoryIfNotExists(Path path) {
        File file = path.toFile();
        if (!file.exists()) {
            return file.mkdir();
        }
        return false;
    }

    private String getFileExtension(String fileName) {
        int lastIndexOf = fileName.lastIndexOf('.');
        return fileName.substring(lastIndexOf);
    }
}
