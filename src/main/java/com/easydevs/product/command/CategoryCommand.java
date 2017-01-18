package com.easydevs.product.command;

import com.easydevs.product.model.Category;

/**
 * Created by ibm on 2017-01-18.
 */
public class CategoryCommand {

    private long id;
    private String name;
    private String description;

    public CategoryCommand() {
    }

    public CategoryCommand(Category category) {
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
}
