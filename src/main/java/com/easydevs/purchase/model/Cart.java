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
    private boolean isTemp;

    /**
     * Instantiates a new Cart.
     */
    public Cart() {
    }

    /**
     * Instantiates a new Cart.
     *
     * @param userId        the user id
     * @param productIdList the product id list
     */
    public Cart(long userId, List<Long> productIdList) {
        this.userId = userId;
        this.productIdList = productIdList;
    }

    /**
     * Instantiates a new Cart.
     *
     * @param userId        the user id
     * @param productIdList the product id list
     * @param isTemp        the is temp
     */
    public Cart(long userId, List<Long> productIdList, boolean isTemp) {
        this.userId = userId;
        this.productIdList = productIdList;
        this.isTemp = isTemp;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() {
        return userId;
    }

    /**
     * Add to cart.
     *
     * @param productId the product id
     */
    public void addToCart(long productId) {
        this.productIdList.add(productId);
    }

    /**
     * Remove from cart.
     *
     * @param productId the product id
     */
    public void removeFromCart(long productId) {
        this.productIdList.remove(this.productIdList.indexOf(productId));
    }

    /**
     * Reset cart.
     */
    public void resetCart() {
        this.productIdList.clear();
    }

    /**
     * Gets is temp.
     *
     * @return the is temp
     */
    public boolean getIsTemp() {
        return isTemp;
    }

    /**
     * Sets is temp.
     *
     * @param isTemp the is temp
     */
    public void setIsTemp(boolean isTemp) {
        this.isTemp = isTemp;
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

    /**
     * Gets product id list.
     *
     * @return the product id list
     */
    public List<Long> getProductIdList() {
        if (productIdList == null) {
            return new ArrayList<>();
        } else {
            return productIdList;
        }
    }
}
