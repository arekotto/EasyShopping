package com.easydevs.product.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by arekotto on 24/01/2017.
 */
public class ProductImage {
    private long productId;
    private byte[] bytes;

    /**
     * Instantiates a new Product image.
     */
    public ProductImage() {
    }

    /**
     * Instantiates a new Product image.
     *
     * @param productId the product id
     * @param bytes     the bytes
     */
    public ProductImage(long productId, byte[] bytes) {
        this.productId = productId;
        this.bytes = bytes;
    }

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Get bytes byte [ ].
     *
     * @return the byte [ ]
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Sets bytes.
     *
     * @param bytes the bytes
     */
    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
