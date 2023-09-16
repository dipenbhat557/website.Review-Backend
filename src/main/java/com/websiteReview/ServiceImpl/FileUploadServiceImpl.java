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
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String uploadImage(String path, MultipartFile file) {
        // Get the original filename of the uploaded file
        String originalFilename = file.getOriginalFilename();

        // Generate a random name using UUID and maintain the original file extension
        String randomName = UUID.randomUUID().toString();
        String randomImageName = randomName.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));

        // Create the full path to save the uploaded image
        String fullPath = path + File.separator + randomImageName;

        // Create the folder if it doesn't exist
        File folderFile = new File(path);

        if (!folderFile.exists()) {
            folderFile.mkdirs();
        }

        try {
            // Copy the input stream of the uploaded file to the specified path
            Files.copy(file.getInputStream(), Paths.get(fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the random image name for storage or retrieval
        return randomImageName;
    }

    @Override
    public List<String> uploadImages(String path, List<MultipartFile> files) {
        List<String> savedImageNames = new ArrayList<>();

        // Iterate through the list of uploaded files
        for (MultipartFile file : files) {
            String originalFilename = file.getOriginalFilename();

            // Generate a random name using UUID and maintain the original file extension
            String randomName = UUID.randomUUID().toString();
            String randomImageName = randomName.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));

            // Create the full path to save the uploaded image
            String fullPath = path + File.separator + randomImageName;

            // Create the folder if it doesn't exist
            File folderFile = new File(path);

            if (!folderFile.exists()) {
                folderFile.mkdirs();
            }

            try {
                // Copy the input stream of the uploaded file to the specified path
                Files.copy(file.getInputStream(), Paths.get(fullPath));

                // Add the saved image name to the list
                savedImageNames.add(randomImageName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Return a list of random image names for storage or retrieval
        return savedImageNames;
    }
}
