package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.FeaturesDto;
import com.websiteReview.Service.FeatureService;

@RestController
@RequestMapping("/feature")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @PostMapping("/create")
    public ResponseEntity<FeaturesDto> createFeatures(@RequestBody FeaturesDto featuresDto) {
        return new ResponseEntity<FeaturesDto>(this.featureService.createFeature(featuresDto), HttpStatus.CREATED);
    }

    @GetMapping("/viewAllFeatures")
    public ResponseEntity<List<FeaturesDto>> viewAllFeatures() {
        return new ResponseEntity<List<FeaturesDto>>(this.featureService.getAllFeatures(), HttpStatus.OK);
    }

    @GetMapping("/viewById/{featureId}")
    public ResponseEntity<FeaturesDto> viewById(@PathVariable int featureId) {
        return new ResponseEntity<FeaturesDto>(this.featureService.getById(featureId), HttpStatus.OK);
    }

    @GetMapping("/delete/{featureId}")
    public ResponseEntity<String> delete(@PathVariable int featureId){
        return new ResponseEntity<String>("Successfully deleted.....", HttpStatus.OK);
    }

}