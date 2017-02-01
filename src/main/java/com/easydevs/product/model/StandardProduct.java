package com.easydevs.product.model;

import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
public class StandardProduct implements Product {

    private long id;
    private long addedByUserId;
    private String name;
    private String manufacturer;
    private String description;
    private double price;
    private long categoryId;
    private String categoryName;
    private boolean hasImage = false;
    private List<Review> reviews;

    public StandardProduct(){

    }

    public StandardProduct(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public long getAddedByUserId() {
        return addedByUserId;
    }

    public void setAddedByUserId(long addedByUserId) {
        this.addedByUserId = addedByUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public long getCategory() { return categoryId; }

    public void setCategory(long category) { this.categoryId = category; }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String category) { this.categoryName = category; }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public List<Review> getReviews() { return reviews; }

    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public boolean isHasReviews() { return this.getReviews() != null && !this.getReviews().isEmpty(); }

    public boolean isReviewedByUserId(Long userId) {
        boolean isReviewed = false;

        if (this.isHasReviews()) {
            for (Review review : this.getReviews()) {
                isReviewed = review.getUserId() == userId;
            }
        }

        return isReviewed;
    }

    public double countAverageRating() {
        if (!this.isHasReviews()) return 0;

        int sum = 0;

        for (Review review: this.getReviews()) {
            sum += review.getRating();
        }

        return (double) sum / this.getReviews().size();
    }

    public String getDescriptionForEmail() {
        return
                "name: " + name +
                ", manufacturer: " + manufacturer +
                ", description: " + description +
                ", price: " + price + '\n';
    }
}
