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

    public ProductCommand() {
    }

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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(long createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public long getCategory() {
        return categoryId;
    }

    public void setCategory(long category) {
        this.categoryId = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasImage() {
        return hasImage;
    }

    public void setHasImage(boolean hasImage) {
        this.hasImage = hasImage;
    }

    public String getCategoryName() { return categoryName; }

    public void setCategoryName(String category) { this.categoryName = category; }

    public boolean isShouldHideAddToCartButton() {
        return shouldHideAddToCartButton;
    }

    public void setShouldHideAddToCartButton(boolean shouldHideAddToCartButton) {
        this.shouldHideAddToCartButton = shouldHideAddToCartButton;
    }

    public List<Review> getReviews() { return reviews; }

    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    public double getAverageRating() { return averageRating; }

    public void setAverageRating(double rating) { averageRating = rating; }

}
