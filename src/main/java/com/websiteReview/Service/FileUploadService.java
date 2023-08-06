package com.websiteReview.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    public String uploadImage(String path, MultipartFile file);

    public List<String> uploadImages(String path, List<MultipartFile> files);
}
