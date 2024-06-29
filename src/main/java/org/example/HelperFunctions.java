package org.example;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class HelperFunctions {

    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    public Path getPathIfNotExists(MultipartFile file){

        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }
        // Ottenere il nome del file originale
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Creare il percorso completo del file
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        return filePath;
    }

}
