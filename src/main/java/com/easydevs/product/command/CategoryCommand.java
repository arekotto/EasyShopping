package com.easydevs.product.command;

import com.easydevs.product.model.Category;

/**
 * Created by ibm on 2017-01-18.
 */
public class CategoryCommand {

    private long id;
    private String name;

    public CategoryCommand() {
    }

    public CategoryCommand(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
