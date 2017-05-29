package com.easydevs.product.command;

import com.easydevs.product.model.Category;

/**
 * Created by ibm on 2017-01-18.
 */
public class CategoryCommand {

    private long id;
    private String name;
    private String description;

    /**
     * Instantiates a new Category command.
     */
    public CategoryCommand() {
    }

    /**
     * Instantiates a new Category command.
     *
     * @param category the category
     */
    public CategoryCommand(Category category) {
        id = category.getId();
        name = category.getName();
        description = category.getDescription();
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() { return id; }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() { return name; }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) { this.id = id; }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() { return description; }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) { this.description = description; }
}
