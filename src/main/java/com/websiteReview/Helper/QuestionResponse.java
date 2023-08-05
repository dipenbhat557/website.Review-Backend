package com.websiteReview.Helper;

import java.util.List;

import com.websiteReview.Dtos.QuestionDto;

public class QuestionResponse {

    private List<QuestionDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;

    public List<QuestionDto> getContent() {
        return content;
    }

    public void setContent(List<QuestionDto> content) {
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

    public QuestionResponse() {
    }

    public QuestionResponse(List<QuestionDto> content, int pageNumber, int pageSize, int totalPages, boolean lastPage) {
        this.content = content;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.lastPage = lastPage;
    }

    @Override
    public String toString() {
        return "QuestionResponse [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
                + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
    }

}
