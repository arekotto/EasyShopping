package com.easydevs.purchase.command;

import com.easydevs.product.model.StandardProduct;
import com.easydevs.purchase.model.PurchaseInvoice;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public class PurchaseInvoiceCommand {

    private long id;
    private List<StandardProduct> productList;
    private double price;
    private long userId;
    private String userName;
    private String shipToAddressStreet;
    private String shipToAddressCity;
    private String shipToAddressCountry;
    private String zipCode;

    public PurchaseInvoiceCommand() {}

    public PurchaseInvoiceCommand(PurchaseInvoice purchaseInvoice) {
        id = purchaseInvoice.getId();
        productList = purchaseInvoice.getProductList();
        price = purchaseInvoice.getPrice();
        userId = purchaseInvoice.getUserId();
        userName = purchaseInvoice.getUserName();
        shipToAddressCity = purchaseInvoice.getShipToAddressCity();
        shipToAddressCountry = purchaseInvoice.getShipToAddressCountry();
        shipToAddressStreet = purchaseInvoice.getShipToAddressStreet();
        zipCode = purchaseInvoice.getZipCode();
    }


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public List<StandardProduct> getProductList() { return productList; }

    public void setProductList(List<StandardProduct> productList) { this.productList = productList; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public long getUserId() { return userId; }

    public void setUserId(long id) { this.userId = id; }

    public String getUserName() { return userName; }

    public void setUserName(String name) { this.userName = name; }

    public String getShipToAddressStreet() { return shipToAddressStreet; }

    public void setShipToAddressStreet(String street) { this.shipToAddressStreet = street; }

    public String getShipToAddressCity() { return shipToAddressCity; }

    public void setShipToAddressCity(String city) { this.shipToAddressCity = city; }

    public String getShipToAddressCountry() { return shipToAddressCountry; }

    public void setShipToAddressCountry(String country) { this.shipToAddressCountry = country; }

    public String getZipCode() { return zipCode; }

    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
}
