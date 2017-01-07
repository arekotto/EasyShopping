package com.easydevs.product.model;

/**
 * Created by Arek on 02.01.2017.
 */
public class StandardProduct implements Product {

    private long id;
    private long addedByUserId;
    private String name;
    private String manufacturer;
    private String description;

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
}
