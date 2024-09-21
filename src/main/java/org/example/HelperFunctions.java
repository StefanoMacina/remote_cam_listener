package org.example;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class HelperFunctions {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    public Path getPathIfNotExists(MultipartFile file){

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename() + "_" + LocalDateTime.now());

        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        return filePath;
    }

}
