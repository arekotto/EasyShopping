package com.easydevs.purchase.model;

import com.easydevs.product.model.StandardProduct;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public class Cart {

    private long userId;
    private List<Long> productIdList;

    public Cart(long userId, List<Long> productIdList) {
        this.userId = userId;
        this.productIdList = productIdList;
    }

    public long getUserId() {
        return userId;
    }

    public void addToCart(long productId) {
        this.productIdList.add(productId);
    }

    public void removeFromCart(long productId) {
        this.productIdList.remove(this.productIdList.indexOf(productId));
    }

    public void resetCart() {
        this.productIdList.clear();
    }

//
//    public void resetCart() {
//        this.productIdList.clear();
//    }
//
//    public double getTotalPrice() {
//        double totalPrice = 0;
//
//        for (StandardProduct standardProduct: this.productList) {
//            totalPrice += standardProduct.getPrice();
//        }
//
//        return totalPrice;
//    }

    public List<Long> getProductIdList() {
        if (productIdList == null) {
            return new ArrayList<>();
        } else {
            return productIdList;
        }
    }
}
