package com.websiteReview.ServiceImpl;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.websiteReview.Service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{
    
    @Override
    public String uploadImage(String path, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String randomName = UUID.randomUUID().toString();
        String randomImageName = randomName.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String fullPath = path + File.separator + randomImageName;

        File folderFile = new File(path);

        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }

        try {

            Files.copy(file.getInputStream(), Paths.get(fullPath));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return randomImageName;
    }

    @Override
    public List<String> uploadImages(String path, List<MultipartFile> files) {
        List<String> savedImageNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();
            String randomName = UUID.randomUUID().toString();
            String randomImageName = randomName.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
            String fullPath = path + File.separator + randomImageName;

            File folderFile = new File(path);

            if (!folderFile.exists()) {
                folderFile.mkdirs();
            }

            try {
                Files.copy(file.getInputStream(), Paths.get(fullPath));
                savedImageNames.add(randomImageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return savedImageNames;
    }
}
