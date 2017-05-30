package com.easydevs.product.service;

import com.easydevs.product.model.Product;
import com.easydevs.product.model.Review;
import com.easydevs.product.model.StandardProduct;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ibm on 2017-05-30.
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations = {"/com/easydevs/applicationContextTest.xml"})
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void cleanUpDb() {
        mongoTemplate.dropCollection(StandardProduct.class);
        mongoTemplate.dropCollection("productIdSequence");

    }

    @Test
    public void createNewProduct() throws Exception {
        StandardProduct product = (StandardProduct) productService.createNewProduct();

        long id = product.getId();
        String name = "test name";
        product.setName(name);
        String manufacturer = "test man";
        product.setManufacturer(manufacturer);
        String description = "desc";
        product.setDescription(description);
        double price = 1.0;
        product.setPrice(price);
        long category = 0;
        product.setCategory(0);
        long createdBy = 0;
        product.setAddedByUserId(createdBy);

        productService.updateProduct(product);

        StandardProduct updatedProduct = (StandardProduct) productService.getProductById(id);

        assertEquals(updatedProduct.getName(), name);
        assertEquals(updatedProduct.getCategory(), category);
        assertEquals(updatedProduct.getAddedByUserId(), createdBy);
        assertEquals(updatedProduct.getDescription(), description);
        assertEquals(updatedProduct.getManufacturer(), manufacturer);
        assertTrue(updatedProduct.getPrice() - price == 0);
    }

    @Test
    public void removeProduct() throws Exception {
        StandardProduct product = (StandardProduct) productService.createNewProduct();

        long id = product.getId();

        productService.removeProduct(product);
        assertNull(productService.getProductById(id));
    }

}