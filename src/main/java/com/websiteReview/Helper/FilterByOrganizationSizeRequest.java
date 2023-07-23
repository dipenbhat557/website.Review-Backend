package com.websiteReview.Helper;

public class FilterByOrganizationSizeRequest {

    private int minSize;
    private int maxSize;

    
    public int getMinSize() {
        return minSize;
    }
    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    public FilterByOrganizationSizeRequest(int minSize, int maxSize) {
        this.minSize = minSize;
        this.maxSize = maxSize;
    }
    public FilterByOrganizationSizeRequest() {
    }
    @Override
    public String toString() {
        return "FilterByOrganizationSizeRequest [minSize=" + minSize + ", maxSize=" + maxSize + "]";
    }

    
    
}
