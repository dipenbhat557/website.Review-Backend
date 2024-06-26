package com.websiteReview.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.websiteReview.Helper.AppConstants;
import com.websiteReview.Helper.SoftwareResponse;
import com.websiteReview.ServiceImpl.SoftwareServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SoftwareServiceImpl softwareService;

    // Handle software search based on a query
    @GetMapping("/{query}")
    @Operation(security = { @SecurityRequirement(name = "BearerJWT") })
    public ResponseEntity<SoftwareResponse> search(@PathVariable String query,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.pageNumberString, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.pageSizeString, required = false) int pageSize) {

        // Call the softwareService to perform the search
        return new ResponseEntity<SoftwareResponse>(this.softwareService.search(query, pageNumber, pageSize),
                HttpStatus.ACCEPTED);
    }
}
