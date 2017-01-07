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

    public ProductCommand() {
    }

    public ProductCommand(StandardProduct standardProduct) {
        id = standardProduct.getId();
        createdByUserId = standardProduct.getAddedByUserId();
        name = standardProduct.getName();
        description = standardProduct.getDescription();
        manufacturer = standardProduct.getManufacturer();
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
}
