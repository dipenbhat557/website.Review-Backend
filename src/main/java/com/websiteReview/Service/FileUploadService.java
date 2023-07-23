package com.websiteReview.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

    public String uploadImage(String path, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String randomName = UUID.randomUUID().toString();
        String randomImageName = randomName.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String fullPath = path + File.separator + randomImageName;

        File folderFile = new File(path);

        if(!folderFile.exists()){
            folderFile.mkdirs();
        }

        try {

            Files.copy(file.getInputStream(), Paths.get(fullPath));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return randomImageName;
    }
    
}
