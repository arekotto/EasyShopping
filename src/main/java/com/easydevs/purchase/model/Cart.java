package com.easydevs.purchase.model;

import com.easydevs.product.model.StandardProduct;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public class Cart {

    private List<StandardProduct> productList;

    public Cart() {}

    public void addToCart(StandardProduct product) {
        this.productList.add(product);
    }

    public void removeFromCart(StandardProduct product) {
        this.productList.remove(this.productList.indexOf(product));
    }

    public void resetCart() {
        this.productList.clear();
    }

    public double getTotalPrice() {
        double totalPrice = 0;

        for (StandardProduct standardProduct: this.productList) {
            totalPrice += standardProduct.getPrice();
        }

        return totalPrice;
    }

    public List<StandardProduct> getProductList() {
        return productList;
    }
}
