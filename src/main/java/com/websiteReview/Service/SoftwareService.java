package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Helper.SoftwareResponse;

public interface SoftwareService {

        public SoftwareDto create(SoftwareDto softwareDto);

        public SoftwareDto viewById(int softwareId);

        public List<SoftwareDto> viewAll();

        public void delete(int softwareId);

        public SoftwareResponse viewByRating(int rating, int pageNumber, int pageSize);

        public SoftwareResponse viewBySubCategory(int subCategoryId, int pageNumber, int pageSize);

        public SoftwareResponse viewBySegment(int sizeId, int pageNumber, int pageSize);

        public SoftwareDto update(int softwareId, SoftwareDto softwareDto);

}
