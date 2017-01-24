package com.easydevs.product.service;

import com.easydevs.product.model.ProductImage;

/**
 * Created by arekotto on 24/01/2017.
 */
public interface ImageService {

    void updateProductImage(ProductImage productImage);

    ProductImage getProductImage(long productId);
}
