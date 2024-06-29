package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Controller
public class Main {
    private static final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);
    }

    @PostMapping("/postpic")
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("clientIp") String clientIp,
            @RequestParam("city") String city,
            @RequestParam("region") String region,
            @RequestParam("country") String country
    ) {

        // Crea la cartella uploads se non esiste
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdir();
        }

        // Ottenere il nome del file originale
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // Creare il percorso completo del file
        Path filePath = Paths.get(UPLOAD_DIR + fileName);

        try {
            // Salvare il file
            Files.write(filePath, file.getBytes());
            System.out.println("""
                    ip : %s
                    city : %s
                    region : %s
                    country : %s
                    """.formatted(clientIp,city,region,country));
            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
