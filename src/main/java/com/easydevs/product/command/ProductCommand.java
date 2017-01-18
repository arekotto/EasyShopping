package com.easydevs.product.command;

import com.easydevs.product.model.StandardProduct;

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
}
