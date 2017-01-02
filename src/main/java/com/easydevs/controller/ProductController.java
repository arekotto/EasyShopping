package com.easydevs.controller;

import com.easydevs.product.command.ProductCreationCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arek on 02.01.2017.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/add")
    public String addNew(Model model) {
        model.addAttribute("productCreationCommand", new ProductCreationCommand());
        return "path to jsp";
    }
}
