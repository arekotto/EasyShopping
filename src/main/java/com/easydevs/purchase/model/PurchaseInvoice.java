package com.easydevs.purchase.model;

import com.easydevs.product.model.StandardProduct;
import com.easydevs.user.model.StandardUser;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public class PurchaseInvoice extends Cart{

    private Long id;
    private List<StandardProduct> productList;
    private double price;
    private long userId;
    private String userName;
    private String shipToAddressStreet;
    private String shipToAddressCity;
    private String shipToAddressCountry;
    private String zipCode;

    public PurchaseInvoice() {
    }

    public PurchaseInvoice(Long id) {
        this.id = id;
    }

    public PurchaseInvoice(long id, List<StandardProduct> productList, StandardUser user) {
        this.id = id;
        this.productList = productList;
        this.userId = user.getId();
        this.userName = user.getName();
        this.shipToAddressCity = user.getCity();
        this.shipToAddressCountry = user.getCountry();
        this.shipToAddressStreet = user.getStreet();
//        this.zipCode = user.getZipCode
        this.price = this.getTotalPrice();
    }

    public long getId() {
        return id;
    }

    @Override
    public List<StandardProduct> getProductList() {
        return productList;
    }

    public double getPrice() {
        return price;
    }

    public long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getShipToAddressStreet() {
        return shipToAddressStreet;
    }

    public String getShipToAddressCity() {
        return shipToAddressCity;
    }

    public String getShipToAddressCountry() {
        return shipToAddressCountry;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setShipToAddressStreet(String shipToAddressStreet) {
        this.shipToAddressStreet = shipToAddressStreet;
    }

    public void setShipToAddressCity(String shipToAddressCity) {
        this.shipToAddressCity = shipToAddressCity;
    }

    public void setShipToAddressCountry(String shipToAddressCountry) {
        this.shipToAddressCountry = shipToAddressCountry;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
