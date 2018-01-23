package com.easydevs.purchase.command;

import com.easydevs.product.model.StandardProduct;

import java.util.List;

/**
 * Created by ibm on 2017-01-19.
 */
public class PurchaseInvoiceCreationCommand {

    private List<StandardProduct> productList;
    private double price;
    private long userId;
    private String userName;
    private String shipToAddressStreet;
    private String shipToAddressCity;
    private String shipToAddressCountry;
    private boolean isCancelled;

    /**
     * Gets product list.
     *
     * @return the product list
     */
    public List<StandardProduct> getProductList() { return productList; }

    /**
     * Sets product list.
     *
     * @param productList the product list
     */
    public void setProductList(List<StandardProduct> productList) { this.productList = productList; }

    /**
     * Gets price.
     *
     * @return the price
     */
    public double getPrice() { return price; }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public long getUserId() { return userId; }

    /**
     * Sets user id.
     *
     * @param id the id
     */
    public void setUserId(long id) { this.userId = id; }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() { return userName; }

    /**
     * Sets user name.
     *
     * @param name the name
     */
    public void setUserName(String name) { this.userName = name; }

    /**
     * Gets ship to address street.
     *
     * @return the ship to address street
     */
    public String getShipToAddressStreet() { return shipToAddressStreet; }

    /**
     * Sets ship to address street.
     *
     * @param street the street
     */
    public void setShipToAddressStreet(String street) { this.shipToAddressStreet = street; }

    /**
     * Gets ship to address city.
     *
     * @return the ship to address city
     */
    public String getShipToAddressCity() { return shipToAddressCity; }

    /**
     * Sets ship to address city.
     *
     * @param city the city
     */
    public void setShipToAddressCity(String city) { this.shipToAddressCity = city; }

    /**
     * Gets ship to address country.
     *
     * @return the ship to address country
     */
    public String getShipToAddressCountry() { return shipToAddressCountry; }

    /**
     * Sets ship to address country.
     *
     * @param country the country
     */
    public void setShipToAddressCountry(String country) { this.shipToAddressCountry = country; }

    public boolean getIsCancelled() { return this.isCancelled; }

    public void setIsCancelled(boolean isCancelled) { this.isCancelled = isCancelled; }
}
