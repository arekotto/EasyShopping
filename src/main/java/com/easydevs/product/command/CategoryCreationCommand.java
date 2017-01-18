package com.easydevs.product.command;

/**
 * Created by ibm on 2017-01-18.
 */
public class CategoryCreationCommand {

    private long id;
    private String name;

    public long getId() { return id; }

    public String getName() { return name; }

    public void setId(long id) { this.id = id; }

    public void setName(String name) { this.name = name; }
}
