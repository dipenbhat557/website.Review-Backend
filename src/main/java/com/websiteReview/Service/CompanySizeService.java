package com.websiteReview.Service;

import java.util.List;

import com.websiteReview.Dtos.CompanySizeDto;

public interface CompanySizeService {

    public CompanySizeDto create(CompanySizeDto companySizeDto);

    public CompanySizeDto viewById(int sizeId);

    public void delete(int sizeId);

    public List<CompanySizeDto> viewAll();
}
