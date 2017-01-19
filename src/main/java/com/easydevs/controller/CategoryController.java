package com.easydevs.controller;

import com.easydevs.product.command.CategoryCommand;
import com.easydevs.product.command.CategoryCreationCommand;
import com.easydevs.product.model.Category;
import com.easydevs.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("categoryCreationCommand", new CategoryCreationCommand());
        return "category_create";
    }

    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("categoryCreationCommand") CategoryCreationCommand categoryCreationCommand) {

        Category newCategory = (Category) categoryService.createNewCategory();
        newCategory.setName(categoryCreationCommand.getName());
        newCategory.setDescription(categoryCreationCommand.getDescription());
        categoryService.updateCategory(newCategory);

        return "redirect:view/" + newCategory.getId();
    }

    @RequestMapping("/view/{categoryId}")
    public String view(Model model,
                       @PathVariable Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);

        if (category != null) {
            model.addAttribute("categoryCommand", new CategoryCommand(category));
            return "category_view";
        }

        return "";
    }

    @RequestMapping("/edit")
    public String update(Model model,
                         @PathVariable Long categoryId,
                         @ModelAttribute("categoryCommand") CategoryCommand categoryCommand) {
        Category category = categoryService.getCategoryById(categoryId);
        if (category != null) {
            model.addAttribute("categoryCommand", new CategoryCommand(category));
            return "category_edit";
        }
        return "redirect:view/" + category.getId();
    }

    @RequestMapping("/save")
    public String save(Model model,
                       @CookieValue("id") String userId,
                       @RequestParam Integer categoryId,
                       @RequestParam Integer addedByUserId,
                       @ModelAttribute("productCommand") CategoryCommand categoryCommand) {
        Category category = categoryService.getCategoryById(categoryId);

        category.setDescription(categoryCommand.getDescription());
        category.setName(categoryCommand.getName());

        model.addAttribute("categoryCommand", new CategoryCommand(category));
        return "redirect:view/" + category.getId();
    }

}
