package com.easydevs.product.service;

import com.easydevs.product.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 2017-01-25.
 */
@Service
public class CategoryService {
    public List<Category> getAll(){
        final List<Category> categoryCommandList = new ArrayList<>();
        categoryCommandList.add(new Category(-1, "Any"));
        categoryCommandList.add(new Category(0, "Creams"));
        categoryCommandList.add(new Category(1, "Perfums"));
        categoryCommandList.add(new Category(2, "Undergarments"));
        categoryCommandList.add(new Category(3, "Jewelry"));
        return categoryCommandList;
    }

    public String getCategoryNameById(Long id){
        switch(id.intValue()){
            case -1:
                return "Any";
            case 0:
                return "Creams";
            case 1:
                return "Perfums";
            case 2:
                return "Undergarments";
            case 3:
                return "Jewelry";
            default:
                return "Unknown category.";
        }
    }
}
