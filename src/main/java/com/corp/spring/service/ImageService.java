package com.corp.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:C:/StudyProjects/spring/images}")
    private final String bucket;

    @SneakyThrows
    public void upload(String imagePath, InputStream content) {
        Path fullImagePath = Path.of(bucket, imagePath);

        try (content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            // byte[] currentByte = new byte[1];
            // while (content.read(currentByte) != -1) {
            //     Files.write(fullImagePath, currentByte, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            // }

        }
    }
}
