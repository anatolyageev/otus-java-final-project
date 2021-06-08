package ru.otus.ageev.bean;

import java.util.List;

public class ProductFilterBean {
    private String productName;
    private List<String> categoryNames;
    private List<String> producerNames;
    private Integer priceMin;
    private Integer priceMax;
    private String sortByField;
    private SortDirection sortDirection;
    private Integer productsPerPage;
    private Integer offset;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(List<String> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public List<String> getProducerNames() {
        return producerNames;
    }

    public void setProducerNames(List<String> producerNames) {
        this.producerNames = producerNames;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public String getSortByField() {
        return sortByField;
    }

    public void setSortByField(String sortByField) {
        this.sortByField = sortByField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public Integer getProductsPerPage() {
        return productsPerPage;
    }

    public void setProductsPerPage(Integer productsPerPage) {
        this.productsPerPage = productsPerPage;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "ProductFilterBean{" +
                "productName='" + productName + '\'' +
                ", categoryNames=" + categoryNames +
                ", producerNames=" + producerNames +
                ", priceMin=" + priceMin +
                ", priceMax=" + priceMax +
                ", sortByField='" + sortByField + '\'' +
                ", sortDirection=" + sortDirection +
                ", productsPerPage=" + productsPerPage +
                ", offset=" + offset +
                '}';
    }
}
