package com.easydevs.product.model;

/**
 * Created by ibm on 2017-01-18.
 */
public class Category {

    private long id;
    private String name;

    public Category() {
    }

    public Category(long id) {
        this.id = id;
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
