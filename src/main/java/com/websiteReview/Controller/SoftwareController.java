package com.websiteReview.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Service.FileUploadService;
import com.websiteReview.Service.SoftwareService;

@RestController
@RequestMapping("/software")
public class SoftwareController {

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private FileUploadService fileUploadService;

    private String path = "src/software/screenshots";

    private String videoPath = "src/software/videos";
    
    @PostMapping("/create")
    public ResponseEntity<SoftwareDto> create(@RequestBody SoftwareDto softwareDto){
        return new ResponseEntity<SoftwareDto>(this.softwareService.createSoftware(softwareDto), HttpStatus.CREATED);
    }

    @GetMapping("/viewById/{softwareId}")
    public ResponseEntity<SoftwareDto> viewById(@PathVariable int softwareId){
        return new ResponseEntity<SoftwareDto>(this.softwareService.getById(softwareId), HttpStatus.OK);
    }

    @GetMapping("/viewAllSoftwares")
    public ResponseEntity<List<SoftwareDto>> viewAllSoftwares(){
        return new ResponseEntity<List<SoftwareDto>>(this.softwareService.getAllSoftwares(), HttpStatus.OK);
    }

    @GetMapping("/delete/{softwareId}")
    public ResponseEntity<String> delete(@PathVariable int softwareId){
        this.softwareService.deleteSoftware(softwareId);
        return new ResponseEntity<String>("Successfully deleted...", HttpStatus.OK);
    }

    @PostMapping("/uploadFiles/{softwareId}")
    public ResponseEntity<?> uploadScreenshots(@PathVariable int softwareId, @RequestParam("screenshots") List<MultipartFile> screenshots, @RequestParam("video") MultipartFile video){

        SoftwareDto softwareDto = this.softwareService.getById(softwareId);
        List<String> screenshotNames = new ArrayList<>();
        String videoName = null;

        try {
            
            screenshotNames = this.fileUploadService.uploadImages(path, screenshots);
            videoName = this.fileUploadService.uploadImage(videoPath, video);
            softwareDto.setScreenshots(screenshotNames);
            softwareDto.setVideoName(videoName);

            SoftwareDto updatedSoftwareDto = this.softwareService.update(softwareId, softwareDto);

            return new ResponseEntity<>(updatedSoftwareDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("Message", "Couldn't upload the screenshots or video....."),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
