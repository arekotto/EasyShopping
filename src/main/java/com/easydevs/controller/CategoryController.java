package com.easydevs.controller;

import com.easydevs.product.command.CategoryCreationCommand;
import com.easydevs.product.model.Category;
import com.easydevs.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ibm on 2017-01-18.
 */
@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model) {
        model.addAttribute("productCreationCommand", new CategoryCreationCommand());
        return "category_create";
    }

    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("productCreationCommand") CategoryCreationCommand categoryCreationCommand) {

        Category newCategory = (Category) categoryService.createNewCategory();
        newCategory.setName(categoryCreationCommand.getName());
        newCategory.setDescription(categoryCreationCommand.getDescription());
        categoryService.updateCategory(newCategory);

        return "redirect:view/" + newCategory.getId();
    }

}
