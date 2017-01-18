package com.easydevs.product.command;

/**
 * Created by Arek on 02.01.2017.
 */
public class ProductCreationCommand {

    private String name;
    private String description;
    private String manufacturer;
    private double price;
    private long categoryId;

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
