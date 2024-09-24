package org.example;

import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class HelperFunctions {

    private static final String PHOTO_DIR = System.getProperty("user.dir") + "/uploads/";
    private static final String INFO_DIR = System.getProperty("user.dir") + "/logInfo/";

    public Path getPathIfNotExists(MultipartFile file){

        File directory = new File(PHOTO_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename() + "_" + LocalDateTime.now());

        return Paths.get(PHOTO_DIR + fileName);
    }

    public void logInfo(String info){
        File dir = new File(INFO_DIR);

        if(!dir.exists()){
            dir.mkdir();
        }

        File logFile = new File( INFO_DIR +  LocalDateTime.now());
        try {
            FileWriter writer = new FileWriter(logFile);
            writer.write(info);
            writer.close();
            System.out.println("log saved in " + logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }






}
