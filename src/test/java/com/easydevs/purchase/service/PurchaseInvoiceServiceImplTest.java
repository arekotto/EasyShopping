package com.easydevs.purchase.service;

import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.ProductService;
import com.easydevs.purchase.model.PurchaseInvoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ibm on 2017-05-31.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"/com/easydevs/applicationContextTest.xml"})
public class PurchaseInvoiceServiceImplTest {

    /**
     * The Purchase service.
     */
    @Autowired
    PurchaseInvoiceService purchaseService;

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * The Mongo template.
     */
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Clean up db.
     */
    @Before
    public void cleanUpDb() {
        mongoTemplate.dropCollection(PurchaseInvoice.class);
        mongoTemplate.dropCollection("invoiceIdSequence");
    }

    /**
     * Create new purchase invoice.
     *
     * @throws Exception the exception
     */
    @Test
    public void createNewPurchaseInvoice() throws Exception {
        PurchaseInvoice invoice = purchaseService.createNewPurchaseInvoice();

        double price = 10.0;
        invoice.setPrice(price);
        long invId = invoice.getId();
        StandardProduct product = (StandardProduct) productService.createNewProduct();
        long productId = product.getId();
        long userId = 0;
        String userName = "heniek";
        List<StandardProduct> productList =  new ArrayList<StandardProduct>();
        productList.add(product);
        invoice.setProductList(productList);
        invoice.setUserId(userId);
        invoice.setUserName(userName);

        purchaseService.updatePurchaseInvoice(invoice);

        PurchaseInvoice invoiceToTest = purchaseService.getPurchaseInvoiceById(invId);

        assertEquals(invoiceToTest.getUserId(), userId);
        assertTrue(invoiceToTest.getPrice() - price == 0);
        assertEquals(invoiceToTest.getUserName(), userName);
        assertNotNull(invoiceToTest.getProductList());

        List<PurchaseInvoice> invoiceList = purchaseService.getPurchaseInvoiceListByUserId(userId);
        assertNotNull(invoiceList);

        PurchaseInvoice invoiceToTest2 = invoiceList.get(0);
        assertEquals(invoiceToTest2.getUserId(), userId);
        assertTrue(invoiceToTest2.getPrice() - price == 0);
        StandardProduct testProduct2 = invoiceToTest2.getProductList().get(0);
        assertNotNull(invoiceToTest.getProductList());
        assertEquals(invoiceToTest2.getUserName(), userName);
    }

    /**
     * Remove purchase invoice.
     *
     * @throws Exception the exception
     */
    @Test
    public void removePurchaseInvoice() throws Exception {
        PurchaseInvoice invoice = purchaseService.createNewPurchaseInvoice();
        long id = invoice.getId();

        purchaseService.removePurchaseInvoice(invoice);
        assertNull(purchaseService.getPurchaseInvoiceById(id));
    }

}