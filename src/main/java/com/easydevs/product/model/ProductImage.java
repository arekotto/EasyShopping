package com.easydevs.product.model;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Created by arekotto on 24/01/2017.
 */
public class ProductImage {
    private long productId;
    private byte[] bytes;

    public ProductImage() {
    }

    public ProductImage(long productId, byte[] bytes) {
        this.productId = productId;
        this.bytes = bytes;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }
}
