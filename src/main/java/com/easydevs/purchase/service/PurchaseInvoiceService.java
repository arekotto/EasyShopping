package com.easydevs.purchase.service;

import com.easydevs.purchase.model.PurchaseInvoice;

import java.io.IOException;

/**
 * Created by ibm on 2017-01-18.
 */
public interface PurchaseInvoiceService {

    PurchaseInvoice getPurchaseInvoiceById(Long id);

    PurchaseInvoice createNewPurchaseInvoice();

    void updatePurchaseInvoice(PurchaseInvoice invoice);

    void removePurchaseInvoice(PurchaseInvoice invoice);

}
