package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.SoftwareDto;
import com.websiteReview.Helper.SoftwareRequest;
import com.websiteReview.Helper.SoftwareResponse;

public interface SoftwareService {

        /**
         * Creates a new software entry in the system.
         *
         * @param softwareRequest The software details to create.
         * @param subCategoryId   The ID of the sub-category to which the software
         *                        belongs.
         * @return The created SoftwareDto.
         */
        public SoftwareDto create(SoftwareRequest softwareRequest, int subCategoryId);

        /**
         * Retrieves a software entry by its ID.
         *
         * @param softwareId The ID of the software to retrieve.
         * @return The SoftwareDto with the specified ID.
         */
        public SoftwareDto viewById(int softwareId);

        /**
         * Retrieves all software entries.
         *
         * @return A list of SoftwareDto representing all software entries.
         */
        public List<SoftwareDto> viewAll();

        /**
         * Deletes a software entry by its ID.
         *
         * @param softwareId The ID of the software to delete.
         */
        public void delete(int softwareId);

        /**
         * Retrieves all purposes associated with a specific software.
         *
         * @param softwareId The ID of the software for which to retrieve purposes.
         * @return A list of strings representing purposes.
         */
        public List<String> viewAllPurposes(int softwareId);

        /**
         * Retrieves software entries by rating with pagination.
         *
         * @param rating     The rating value.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of software entries per page.
         * @return A SoftwareResponse containing software entries with the specified
         *         rating.
         */
        public SoftwareResponse viewByRating(int rating, int pageNumber, int pageSize);

        /**
         * Retrieves software entries by sub-category with pagination.
         *
         * @param subCategoryId The ID of the sub-category for which to retrieve
         *                      software entries.
         * @param pageNumber    The page number for pagination.
         * @param pageSize      The number of software entries per page.
         * @return A SoftwareResponse containing software entries for the specified
         *         sub-category.
         */
        public SoftwareResponse viewBySubCategory(int subCategoryId, int pageNumber, int pageSize);

        /**
         * Retrieves software entries by organization size segment with pagination.
         *
         * @param sizeId     The ID of the organization size segment for which to
         *                   retrieve software entries.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of software entries per page.
         * @return A SoftwareResponse containing software entries for the specified
         *         organization size segment.
         */
        public SoftwareResponse viewBySegment(int sizeId, int pageNumber, int pageSize);

        /**
         * Updates a software entry with new details.
         *
         * @param softwareId  The ID of the software to update.
         * @param softwareDto The updated software details.
         * @return The updated SoftwareDto.
         */
        public SoftwareDto update(int softwareId, SoftwareDto softwareDto);

        /**
         * Searches for software entries matching a query string with pagination.
         *
         * @param query      The search query.
         * @param pageNumber The page number for pagination.
         * @param pageSize   The number of software entries per page.
         * @return A SoftwareResponse containing software entries matching the search
         *         query.
         */
        public SoftwareResponse search(String query, int pageNumber, int pageSize);
}
