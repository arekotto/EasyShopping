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

    /**
     * Instantiates a new Standard product.
     */
    public StandardProduct(){

    }

    /**
     * Instantiates a new Standard product.
     *
     * @param id the id
     */
    public StandardProduct(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    /**
     * Gets added by user id.
     *
     * @return the added by user id
     */
    public long getAddedByUserId() {
        return addedByUserId;
    }

    /**
     * Sets added by user id.
     *
     * @param addedByUserId the added by user id
     */
    public void setAddedByUserId(long addedByUserId) {
        this.addedByUserId = addedByUserId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() { return price; }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets manufacturer.
     *
     * @return the manufacturer
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets manufacturer.
     *
     * @param manufacturer the manufacturer
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public long getCategory() { return categoryId; }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(long category) { this.categoryId = category; }

    /**
     * Gets category name.
     *
     * @return the category name
     */
    public String getCategoryName() { return categoryName; }

    /**
     * Sets category name.
     *
     * @param category the category
     */
    public void setCategoryName(String category) { this.categoryName = category; }

    /**
     * Is has image boolean.
     *
     * @return the boolean
     */
    public boolean isHasImage() {
        return hasImage;
    }

    /**
     * Sets has image.
     *
     * @param hasImage the has image
     */
    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    /**
     * Gets reviews.
     *
     * @return the reviews
     */
    public List<Review> getReviews() { return reviews; }

    /**
     * Sets reviews.
     *
     * @param reviews the reviews
     */
    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    /**
     * Is has reviews boolean.
     *
     * @return the boolean
     */
    public boolean isHasReviews() { return this.getReviews() != null && !this.getReviews().isEmpty(); }

    /**
     * Is reviewed by user id boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public boolean isReviewedByUserId(Long userId) {
        boolean isReviewed = false;

        if (this.isHasReviews()) {
            for (Review review : this.getReviews()) {
                isReviewed = review.getUserId() == userId;
            }
        }

        return isReviewed;
    }

    /**
     * Count average rating double.
     *
     * @return the double
     */
    public double countAverageRating() {
        if (!this.isHasReviews()) return 0;

        int sum = 0;

        for (Review review: this.getReviews()) {
            sum += review.getRating();
        }

        return (double) sum / this.getReviews().size();
    }

    /**
     * Gets description for email.
     *
     * @return the description for email
     */
    public String getDescriptionForEmail() {
        return
                "name: " + name +
                ", manufacturer: " + manufacturer +
                ", description: " + description +
                ", price: " + price + '\n';
    }
}
