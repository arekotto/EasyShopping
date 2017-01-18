package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.command.ProductCreationCommand;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model) {
        model.addAttribute("productCreationCommand", new ProductCreationCommand());
        return "product_create";
    }

    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("productCreationCommand") ProductCreationCommand productCreationCommand) {

        StandardProduct newProduct = (StandardProduct) productService.createNewProduct();
        newProduct.setName(productCreationCommand.getName());
        newProduct.setDescription(productCreationCommand.getDescription());
        newProduct.setManufacturer(productCreationCommand.getManufacturer());
        newProduct.setAddedByUserId(Long.parseLong(userId));
        newProduct.setCategory(productCreationCommand.getCategory());
        newProduct.setPrice(productCreationCommand.getPrice());
        productService.updateProduct(newProduct);

        return "redirect:view/" + newProduct.getId();
    }

    @RequestMapping("/view/{productId}")
    public String view(Model model, @PathVariable Long productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product != null) {
            model.addAttribute("productCommand", new ProductCommand(product));
            return "product_view";

        }

        return "";
    }

    @RequestMapping("/remove")
    public String remove(Model model, @CookieValue("id") String userId, @RequestParam Integer productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (Long.parseLong(userId) == product.getAddedByUserId()) {
            productService.removeProduct(product);
            return "redirect:user";

        }

        return "redirect:view/" + productId;
    }

    @RequestMapping("/user")
    public String viewUsers(Model model, @CookieValue("id") String userId) {
        List<StandardProduct> standardProducts = productService.getProductsByUserId(Long.parseLong(userId));
        List<ProductCommand> productCommandList = new ArrayList<>();
        for (StandardProduct standardProduct : standardProducts) {
            productCommandList.add(new ProductCommand(standardProduct));
        }
        model.addAttribute("productCommandList", productCommandList);
        model.addAttribute("isOnlyForUser", true);
        return "product_all";
    }

    @RequestMapping("/all")
    public String viewAll(Model model) {
        List<StandardProduct> standardProducts = productService.getAll();
        List<ProductCommand> productCommandList = new ArrayList<>();
        for (StandardProduct standardProduct : standardProducts) {
            productCommandList.add(new ProductCommand(standardProduct));
        }
        model.addAttribute("productCommandList", productCommandList);
        model.addAttribute("isOnlyForUser", false);
        return "product_all";
    }

    @RequestMapping("/{category}")
    public String viewByCategory(Model model, @PathVariable long categoryId) {
        List<StandardProduct> standardProducts = productService.getProductsByCategory(categoryId);
        List<ProductCommand> productCommandList = new ArrayList<>();

        for (StandardProduct standardProduct: standardProducts) {
            productCommandList.add(new ProductCommand(standardProduct));
        }

        model.addAttribute("productCommandList", productCommandList);
        return "product_all";
    }
}
