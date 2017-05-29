package com.easydevs.product.command;

/**
 * Created by ibm on 2017-01-18.
 */
public class CategoryCreationCommand {

    private long id;
    private String name;
    private String description;

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
