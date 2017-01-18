package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.model.Product;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.ProductService;
import com.easydevs.purchase.model.Cart;
import com.easydevs.purchase.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arekotto on 18/01/2017.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;

    @RequestMapping("/view")
    public String view(Model model, @CookieValue("id") String userId) {

        Cart userCart = cartService.getCartForUser(Long.parseLong(userId));
        List<ProductCommand> productCommandList = new ArrayList<>();

        if (userCart != null) {
            for (Long productId : userCart.getProductIdList()) {
                StandardProduct product = (StandardProduct) productService.getProductById(productId);
                productCommandList.add(new ProductCommand(product));

            }
        }


        model.addAttribute("productCommandList", productCommandList);
        return "cart";
    }

    @RequestMapping("/add")
    public String add(Model model, @CookieValue("id") String userId, @RequestParam Long productId) {

        long userIdLong = Long.parseLong(userId);
        Cart userCart = cartService.getCartForUser(userIdLong);
        if (userCart == null) {
            userCart = cartService.createNewCart(userIdLong);
        }
        if (productService.getProductById(productId) != null) {
            userCart.addToCart(productId);
            cartService.updateCartForUser(userIdLong, userCart);

        }


        return "redirect:view";
    }

    @RequestMapping("/remove")
    public String remove(Model model, @CookieValue("id") String userId, @RequestParam Long productId) {
        long userIdLong = Long.parseLong(userId);
        Cart userCart = cartService.getCartForUser(userIdLong);
        if (userCart != null) {
            userCart.removeFromCart(productId);
            cartService.updateCartForUser(userIdLong, userCart);
        }

        return "redirect:view";
    }
}
