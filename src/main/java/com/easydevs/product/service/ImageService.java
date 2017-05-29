package com.easydevs.product.service;

import com.easydevs.product.model.ProductImage;

/**
 * Created by arekotto on 24/01/2017.
 */
public interface ImageService {

    /**
     * Update product image.
     *
     * @param productImage the product image
     */
    void updateProductImage(ProductImage productImage);

    /**
     * Gets product image.
     *
     * @param productId the product id
     * @return the product image
     */
    ProductImage getProductImage(long productId);

    /**
     * Remove product image.
     *
     * @param productId the product id
     */
    void removeProductImage(long productId);
}
