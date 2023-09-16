package com.websiteReview.Service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    /**
     * Uploads a single image file to the specified path.
     *
     * @param path The destination path where the image should be saved.
     * @param file The image file to be uploaded.
     * @return The filename of the uploaded image.
     */
    public String uploadImage(String path, MultipartFile file);

    /**
     * Uploads multiple image files to the specified path.
     *
     * @param path  The destination path where the images should be saved.
     * @param files The list of image files to be uploaded.
     * @return A list of filenames of the uploaded images.
     */
    public List<String> uploadImages(String path, List<MultipartFile> files);
}
