package com.easydevs.purchase.service;

import com.easydevs.purchase.model.PurchaseInvoice;

import java.io.IOException;
import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public interface PurchaseInvoiceService {

    /**
     * Gets purchase invoice by id.
     *
     * @param id the id
     * @return the purchase invoice by id
     */
    PurchaseInvoice getPurchaseInvoiceById(Long id);

    /**
     * Gets purchase invoice list by user id.
     *
     * @param userId the user id
     * @return the purchase invoice list by user id
     */
    List<PurchaseInvoice> getPurchaseInvoiceListByUserId(long userId);

    /**
     * Create new purchase invoice purchase invoice.
     *
     * @return the purchase invoice
     */
    PurchaseInvoice createNewPurchaseInvoice();

    /**
     * Update purchase invoice.
     *
     * @param invoice the invoice
     */
    void updatePurchaseInvoice(PurchaseInvoice invoice);

    /**
     * Remove purchase invoice.
     *
     * @param invoice the invoice
     */
    void removePurchaseInvoice(PurchaseInvoice invoice);

}
