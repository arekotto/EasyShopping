package com.easydevs.product.command;

import com.easydevs.product.model.Review;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.CategoryService;

import java.util.List;

/**
 * Created by Arek on 07.01.2017.
 */
public class ProductCommand {

    private long id;
    private long createdByUserId;
    private String name;
    private String description;
    private String manufacturer;
    private double price;
    private long categoryId;
    private String categoryName;
    private boolean hasImage;
    private boolean shouldHideAddToCartButton = false;
    private List<Review> reviews;
    private double averageRating;

    /**
     * Instantiates a new Product command.
     */
    public ProductCommand() {
    }

    /**
     * Instantiates a new Product command.
     *
     * @param standardProduct the standard product
     */
    public ProductCommand(StandardProduct standardProduct) {
        id = standardProduct.getId();
        createdByUserId = standardProduct.getAddedByUserId();
        name = standardProduct.getName();
        description = standardProduct.getDescription();
        manufacturer = standardProduct.getManufacturer();
        categoryId = standardProduct.getCategory();
        price = standardProduct.getPrice();
        hasImage = standardProduct.isHasImage();
        reviews = standardProduct.getReviews();
    }


    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets created by user id.
     *
     * @return the created by user id
     */
    public long getCreatedByUserId() {
        return createdByUserId;
    }

    /**
     * Sets created by user id.
     *
     * @param createdByUserId the created by user id
     */
    public void setCreatedByUserId(long createdByUserId) {
        this.createdByUserId = createdByUserId;
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
    public long getCategory() {
        return categoryId;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(long category) {
        this.categoryId = category;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

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
     * Is should hide add to cart button boolean.
     *
     * @return the boolean
     */
    public boolean isShouldHideAddToCartButton() {
        return shouldHideAddToCartButton;
    }

    /**
     * Sets should hide add to cart button.
     *
     * @param shouldHideAddToCartButton the should hide add to cart button
     */
    public void setShouldHideAddToCartButton(boolean shouldHideAddToCartButton) {
        this.shouldHideAddToCartButton = shouldHideAddToCartButton;
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
     * Gets average rating.
     *
     * @return the average rating
     */
    public double getAverageRating() { return averageRating; }

    /**
     * Sets average rating.
     *
     * @param rating the rating
     */
    public void setAverageRating(double rating) { averageRating = rating; }

}
