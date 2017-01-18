package com.easydevs.product.service;

import com.easydevs.product.model.Category;

import java.util.List;

/**
 * Created by ibm on 2017-01-18.
 */
public interface CategoryService {

    Category getCategoryById(long categoryId);

    Category createNewCategory();

    void updateCategory(Category category);

    void removeCategory(Category category);

    List<Category> getAll();

    List<Category> search(String searchQuery);
}
