package com.easydevs.purchase.model;

import com.easydevs.product.model.StandardProduct;
import com.easydevs.user.model.StandardUser;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public class PurchaseInvoice {

    private Long id;
    private List<StandardProduct> productList;
    private double price;
    private long userId;
    private String userName;
    private String shipToAddressStreet;
    private String shipToAddressCity;
    private String shipToAddressCountry;
    private boolean isCancelled;

    /**
     * Instantiates a new Purchase invoice.
     */
    public PurchaseInvoice() {
    }

    /**
     * Instantiates a new Purchase invoice.
     *
     * @param id the id
     */
    public PurchaseInvoice(Long id) {
        this.id = id;
    }

    /**
     * Instantiates a new Purchase invoice.
     *
     * @param id          the id
     * @param productList the product list
     * @param user        the user
     */
    public PurchaseInvoice(long id, List<StandardProduct> productList, StandardUser user) {
        this.id = id;
        this.productList = productList;
        this.userId = user.getId();
        this.userName = user.getName();
        this.shipToAddressCity = user.getCity();
        this.shipToAddressCountry = user.getCountry();
        this.shipToAddressStreet = user.getStreet();
        this.isCancelled = false;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets product list.
     *
     * @return the product list
     */
    public List<StandardProduct> getProductList() {
        return productList;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() {
        return price;
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
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets ship to address street.
     *
     * @return the ship to address street
     */
    public String getShipToAddressStreet() {
        return shipToAddressStreet;
    }

    /**
     * Gets ship to address city.
     *
     * @return the ship to address city
     */
    public String getShipToAddressCity() {
        return shipToAddressCity;
    }

    /**
     * Gets ship to address country.
     *
     * @return the ship to address country
     */
    public String getShipToAddressCountry() {
        return shipToAddressCountry;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets ship to address street.
     *
     * @param shipToAddressStreet the ship to address street
     */
    public void setShipToAddressStreet(String shipToAddressStreet) {
        this.shipToAddressStreet = shipToAddressStreet;
    }

    /**
     * Sets ship to address city.
     *
     * @param shipToAddressCity the ship to address city
     */
    public void setShipToAddressCity(String shipToAddressCity) {
        this.shipToAddressCity = shipToAddressCity;
    }

    /**
     * Sets ship to address country.
     *
     * @param shipToAddressCountry the ship to address country
     */
    public void setShipToAddressCountry(String shipToAddressCountry) { this.shipToAddressCountry = shipToAddressCountry; }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(long userId) { this.userId = userId; }

    /**
     * Sets user name.
     *
     * @param userName the user name
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * Sets product list.
     *
     * @param productList the product list
     */
    public void setProductList(List<StandardProduct> productList) { this.productList = productList; }

    /**
     * Calculate current price.
     */
    public void calculateCurrentPrice() {
        double totalPrice = 0;

        for (StandardProduct standardProduct: this.productList) {
            totalPrice += standardProduct.getPrice();
        }

        this.price = Math.round(totalPrice * 100.0) / 100.0;
    }

    public boolean getIsCancelled() { return this.isCancelled; }

    public void setIsCancelled(boolean isCancelled) { this.isCancelled = isCancelled; }
}
