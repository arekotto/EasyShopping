package com.easydevs.product.service;


import com.easydevs.product.model.Category;
import com.easydevs.product.model.Product;
import com.easydevs.product.model.Review;
import com.easydevs.product.model.StandardProduct;
//import com.sun.xml.internal.bind.api.impl.NameConverter;

import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
public interface ProductService {
    /**
     * Gets product by id.
     *
     * @param productId the product id
     * @return the product by id
     */
    Product getProductById(long productId);

    /**
     * Create new product product.
     *
     * @return the product
     */
    Product createNewProduct();

    /**
     * Rate product.
     *
     * @param productId the product id
     * @param review    the review
     */
    void rateProduct(long productId, Review review);

    /**
     * Update product.
     *
     * @param product the product
     */
    void updateProduct(Product product);

    /**
     * Remove product.
     *
     * @param product the product
     */
    void removeProduct(Product product);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<StandardProduct> getAll();

    /**
     * Gets products by user id.
     *
     * @param userId the user id
     * @return the products by user id
     */
    List<StandardProduct> getProductsByUserId(long userId);

    /**
     * Gets products by category.
     *
     * @param categoryId the category id
     * @return the products by category
     */
    List<StandardProduct> getProductsByCategory(long categoryId);

    /**
     * Gets all categories.
     *
     * @return the all categories
     */
    List<Category> getAllCategories();

    /**
     * Search list.
     *
     * @param searchQuery    the search query
     * @param searchCategory the search category
     * @return the list
     */
    List<StandardProduct> search(String searchQuery, long searchCategory);

    /**
     * Search list.
     *
     * @param searchQuery the search query
     * @return the list
     */
    List<StandardProduct> search(String searchQuery);
}
