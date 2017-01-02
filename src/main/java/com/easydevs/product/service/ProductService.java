package com.easydevs.product.service;


import com.easydevs.product.model.Product;

/**
 * Created by Arek on 02.01.2017.
 */
public interface ProductService {
    Product getProductById(long productId);

    Product createNewProduct();

    void updateProduct(Product product);
}
