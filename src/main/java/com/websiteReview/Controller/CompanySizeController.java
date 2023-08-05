package com.websiteReview.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Service.CompanySizeService;

@RestController
@RequestMapping("company/size")
public class CompanySizeController {

    @Autowired
    private CompanySizeService companySizeService;

    @PostMapping("/create")
    public ResponseEntity<CompanySizeDto> createCompanySize(CompanySizeDto companySizeDto) {
        return new ResponseEntity<CompanySizeDto>(this.companySizeService.create(companySizeDto),
                HttpStatus.CREATED);
    }

    @GetMapping("/viewById/{sizeId}")
    public ResponseEntity<CompanySizeDto> viewById(@PathVariable int sizeId) {
        return new ResponseEntity<CompanySizeDto>(this.companySizeService.viewById(sizeId), HttpStatus.OK);
    }

    @GetMapping("/delete/{sizeId}")
    public ResponseEntity<String> deleteSize(@PathVariable int sizeId) {
        this.companySizeService.delete(sizeId);
        return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
    }

    @GetMapping("/viewAll")
    public ResponseEntity<List<CompanySizeDto>> viewAllSizes() {
        return new ResponseEntity<List<CompanySizeDto>>(this.companySizeService.viewAll(), HttpStatus.OK);
    }

}
