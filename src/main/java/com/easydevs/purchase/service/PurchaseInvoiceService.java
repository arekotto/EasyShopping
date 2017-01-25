package com.easydevs.purchase.service;

import com.easydevs.purchase.model.PurchaseInvoice;

import java.io.IOException;
import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public interface PurchaseInvoiceService {

    PurchaseInvoice getPurchaseInvoiceById(Long id);

    List<PurchaseInvoice> getPurchaseInvoiceListByUserId(long userId);

    PurchaseInvoice createNewPurchaseInvoice();

    void updatePurchaseInvoice(PurchaseInvoice invoice);

    void removePurchaseInvoice(PurchaseInvoice invoice);

}
