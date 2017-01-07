package com.easydevs.product.service;


import com.easydevs.product.model.Product;
import com.easydevs.product.model.StandardProduct;

import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
public interface ProductService {
    Product getProductById(long productId);

    Product createNewProduct();

    void updateProduct(Product product);

    List<StandardProduct> getAll();
}
