package ru.otus.ageev.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product extends LongID {
    private String name;
    private String producer;
    private String category;
    private String shortDescription;
    private BigDecimal price;
    private String image;
    private String metaTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(name, product.name) &&
                Objects.equals(shortDescription, product.shortDescription) &&
                Objects.equals(price, product.price) &&
                Objects.equals(image, product.image) &&
                Objects.equals(metaTitle, product.metaTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, shortDescription, price, image, metaTitle);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", category='" + category + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", metaTitle='" + metaTitle + '\'' +
                ", id=" + id +
                '}';
    }
}
