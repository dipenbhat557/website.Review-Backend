package com.websiteReview.Service;

import java.util.List;
import com.websiteReview.Dtos.PricingDto;
import com.websiteReview.Helper.PricingRequest;

public interface PricingService {

    /**
     * Creates a new pricing entry for a software.
     *
     * @param pricingRequest The pricing information to be created.
     * @param softwareId     The ID of the software for which the pricing is
     *                       created.
     * @return The created PricingDto.
     */
    public PricingDto create(PricingRequest pricingRequest, int softwareId);

    /**
     * Retrieves a pricing entry by its ID.
     *
     * @param pricingId The ID of the pricing entry to retrieve.
     * @return The PricingDto with the specified ID.
     */
    public PricingDto viewById(int pricingId);

    /**
     * Deletes a pricing entry by its ID.
     *
     * @param pricingId The ID of the pricing entry to delete.
     */
    public void delete(int pricingId);

    /**
     * Retrieves a list of pricing entries associated with a software.
     *
     * @param softwareId The ID of the software for which to retrieve pricing
     *                   entries.
     * @return A list of PricingDto objects associated with the specified software.
     */
    public List<PricingDto> viewBySoftware(int softwareId);
}
