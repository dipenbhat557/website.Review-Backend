package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Helper.CompanySizeRequest;

public interface CompanySizeService {

    /**
     * Create a new company size based on the provided CompanySizeRequest.
     *
     * @param companySizeRequest The request containing information to create the
     *                           company size.
     * @return The created CompanySizeDto.
     */
    public CompanySizeDto create(CompanySizeRequest companySizeRequest);

    /**
     * View a company size by its unique identifier.
     *
     * @param sizeId The unique ID of the company size to view.
     * @return The CompanySizeDto representing the company size.
     */
    public CompanySizeDto viewById(int sizeId);

    /**
     * Delete a company size by its unique identifier.
     *
     * @param sizeId The unique ID of the company size to delete.
     */
    public void delete(int sizeId);

    /**
     * View all company sizes.
     *
     * @return A list of CompanySizeDto representing all available company sizes.
     */
    public List<CompanySizeDto> viewAll();
}
