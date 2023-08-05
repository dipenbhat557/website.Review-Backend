package com.websiteReview.Helper;

import java.util.List;

import com.websiteReview.Dtos.SoftwareDto;

public class SoftwareResponse {

    private List<SoftwareDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;

    public List<SoftwareDto> getContent() {
        return content;
    }

    public void setContent(List<SoftwareDto> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return lastPage;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }

    public SoftwareResponse() {
    }

    public SoftwareResponse(List<SoftwareDto> content, int pageNumber, int pageSize, int totalPages, boolean lastPage) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        return "SoftwareResponse [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
                + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
    }

}
