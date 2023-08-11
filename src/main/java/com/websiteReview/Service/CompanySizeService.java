package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CompanySizeDto;
import com.websiteReview.Helper.CompanySizeRequest;

public interface CompanySizeService {

    public CompanySizeDto create(CompanySizeRequest companySizeRequest);

    public CompanySizeDto viewById(int sizeId);

    public void delete(int sizeId);

    public List<CompanySizeDto> viewAll();
}
