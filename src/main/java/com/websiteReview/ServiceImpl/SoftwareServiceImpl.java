package com.websiteReview.ServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Exception.ResourceNotFoundException;
import com.websiteReview.Helper.ModelToDto;
import com.websiteReview.Helper.SoftwareRequest;
import com.websiteReview.Helper.SoftwareResponse;
import com.websiteReview.Model.Category;
import com.websiteReview.Model.CompanySize;
import com.websiteReview.Model.Software;
import com.websiteReview.Model.SubCategory;
import com.websiteReview.Respository.CompanySizeRepository;
import com.websiteReview.Respository.SoftwareRepository;
import com.websiteReview.Respository.SubCategoryRepository;
import com.websiteReview.Service.SoftwareService;

@Service
public class SoftwareServiceImpl implements SoftwareService {

        @Autowired
        private SoftwareRepository softwareRepository;

        @Autowired
        private SubCategoryRepository subCategoryRepository;

        @Autowired
        private CompanySizeRepository companySizeRepository;

        @Autowired
        private ModelToDto ModelToDto;

        // Create a new software.
        @Override
        public SoftwareDto create(SoftwareRequest softwareRequest, int subCategoryId) {
                Software software = new Software();
                software.setTitle(softwareRequest.getTitle());
                software.setDescription(softwareRequest.getDescription());
                software.setLocation(softwareRequest.getLocation());
                software.setYearFounded(softwareRequest.getYearFounded());
                software.setLanguage(softwareRequest.getLanguage());
                software.setDifferenceFromOthers(softwareRequest.getDifferenceFromOthers());
                software.setWebsiteLink(softwareRequest.getWebsiteLink());
                software.setTwitterId(softwareRequest.getTwitterId());
                software.setLinkedInId(softwareRequest.getLinkedInId());
                software.setFeatures(softwareRequest.getFeatures());

                SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected resource is not found"));
                software.setSubCategory(subCategory);

                int companySizeId = softwareRequest.getCompanySizeId();
                CompanySize companySize = this.companySizeRepository.findById(companySizeId).orElseThrow(
                                () -> new ResourceNotFoundException("The expected company size is not found"));
                software.setCompanySize(companySize);

                software = this.softwareRepository.save(software);

                return ModelToDto.software(software);
        }

        // View a software by its ID.
        @Override
        public SoftwareDto viewById(int softwareId) {
                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));
                return ModelToDto.software(software);
        }

        // View all software products.
        @Override
        public List<SoftwareDto> viewAll() {
                List<Software> softwares = this.softwareRepository.findAll();
                List<SoftwareDto> softwareDtos = softwares.stream()
                                .map(software -> ModelToDto.software(software))
                                .collect(Collectors.toList());
                return softwareDtos;
        }

        // Delete a software by its ID.
        @Override
        public void delete(int softwareId) {
                this.softwareRepository.delete(this.softwareRepository.findById(softwareId).orElseThrow(
                                () -> new ResourceNotFoundException(
                                                "The expected resource is not found while trying to delete it")));
        }

        // View all unique purposes of a software.
        @Override
        public List<String> viewAllPurposes(int softwareId) {
                Software software = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException("The expected software is not found"));

                SubCategory subCategory = software.getSubCategory();
                List<Category> categories = subCategory.getCategories();

                Set<String> uniquePurposes = new HashSet<>(); // Use a Set to ensure non-repetitive purposes

                categories.forEach(category -> {
                        List<String> categoryPurposes = category.getPurposes();
                        categoryPurposes.forEach(purpose -> uniquePurposes.add(purpose));
                });

                return new ArrayList<>(uniquePurposes); // Convert Set to List
        }

        // View software products by rating range with pagination.
        @Override
        public SoftwareResponse viewByRating(int rating, int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Software> page = this.softwareRepository.findByRatingRange(rating, rating + 1, pageable);
                List<Software> pageSoftware = page.getContent();

                List<SoftwareDto> pageSoftwareDtos = pageSoftware.stream()
                                .map(software -> ModelToDto.software(software))
                                .collect(Collectors.toList());

                SoftwareResponse response = new SoftwareResponse();
                response.setContent(pageSoftwareDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        // View software products by sub-category with pagination.
        @Override
        public SoftwareResponse viewBySubCategory(int subCategoryId, int pageNumber, int pageSize) {
                SubCategory subCategory = this.subCategoryRepository.findById(subCategoryId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The requested sub category is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Software> page = this.softwareRepository.findBySubCategory(subCategory, pageable);
                List<Software> pageSoftwares = page.getContent();

                List<SoftwareDto> pageSoftwareDtos = pageSoftwares.stream()
                                .map(software -> ModelToDto.software(software))
                                .collect(Collectors.toList());

                SoftwareResponse response = new SoftwareResponse();
                response.setContent(pageSoftwareDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        // View software products by company size segment with pagination.
        @Override
        public SoftwareResponse viewBySegment(int sizeId, int pageNumber, int pageSize) {
                CompanySize companySize = this.companySizeRepository.findById(sizeId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected company size is not found"));

                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Software> page = this.softwareRepository.findByCompanySize(companySize, pageable);
                List<Software> pageSoftwares = page.getContent();

                List<SoftwareDto> pageSoftwareDtos = pageSoftwares.stream()
                                .map(software -> ModelToDto.software(software))
                                .collect(Collectors.toList());

                SoftwareResponse response = new SoftwareResponse();
                response.setContent(pageSoftwareDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }

        // Update a software by its ID.
        @Override
        public SoftwareDto update(int softwareId, SoftwareDto softwareDto) {
                Software oldSoftware = this.softwareRepository.findById(softwareId)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "The expected software has not been found while updating the screenshots..."));

                oldSoftware.setTitle(softwareDto.getTitle());
                oldSoftware.setDescription(softwareDto.getDescription());
                oldSoftware.setLocation(softwareDto.getLocation());
                oldSoftware.setYearFounded(softwareDto.getYearFounded());
                oldSoftware.setLanguage(softwareDto.getLanguage());
                oldSoftware.setDifferenceFromOthers(softwareDto.getDifferenceFromOthers());
                oldSoftware.setProfileImageName(softwareDto.getProfileImageName());
                oldSoftware.setWebsiteLink(softwareDto.getWebsiteLink());
                oldSoftware.setTwitterId(softwareDto.getTwitterId());
                oldSoftware.setLinkedInId(softwareDto.getLinkedInId());
                oldSoftware.setFeatures(softwareDto.getFeatures());
                oldSoftware.setVideoName(softwareDto.getVideoName());
                oldSoftware.setScreenshots(softwareDto.getScreenshots());
                oldSoftware.setNoOfResponses(softwareDto.getNoOfResponses());
                oldSoftware.setRating(softwareDto.getRating());
                oldSoftware.setNotionDirectionRating(softwareDto.getNotionDirectionRating());
                oldSoftware.setEaseOfUseRating(softwareDto.getEaseOfUseRating());
                oldSoftware.setMeetsRequirementRating(softwareDto.getMeetsRequirementRating());
                oldSoftware.setQualitySupportRating(softwareDto.getQualitySupportRating());

                return ModelToDto.software(this.softwareRepository.save(oldSoftware));
        }

        // Search for software products by title with pagination.
        public SoftwareResponse search(String query, int pageNumber, int pageSize) {
                Pageable pageable = PageRequest.of(pageNumber, pageSize);
                Page<Software> page = this.softwareRepository.findByTitleContainingIgnoreCase(query, pageable);
                List<Software> pageSoftwares = page.getContent();

                List<SoftwareDto> pageSoftwareDtos = pageSoftwares.stream()
                                .map(software -> ModelToDto.software(software))
                                .collect(Collectors.toList());

                SoftwareResponse response = new SoftwareResponse();
                response.setContent(pageSoftwareDtos);
                response.setPageNumber(page.getNumber());
                response.setPageSize(page.getSize());
                response.setTotalPages(page.getTotalPages());
                response.setLastPage(page.isLast());

                return response;
        }
}
