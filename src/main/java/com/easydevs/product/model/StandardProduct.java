package com.easydevs.product.model;

/**
 * Created by Arek on 02.01.2017.
 */
public class StandardProduct implements Product {

    private long id;

    public StandardProduct(){

    }

    public StandardProduct(long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
