package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.model.Product;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.ProductService;
import com.easydevs.purchase.model.Cart;
import com.easydevs.purchase.service.CartService;
import com.easydevs.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arekotto on 18/01/2017.
 */
@Controller
@RequestMapping("/cart")
public class CartController {

    /**
     * The constant JSP_PATH_PREFIX.
     */
    final static String JSP_PATH_PREFIX = "cart/";

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    /**
     * View string.
     *
     * @param model      the model
     * @param userId     the user id
     * @param tempUserId the temp user id
     * @param request    the request
     * @return the string
     */
    @RequestMapping("/view")
    public String view(Model model,
                       @CookieValue(value = "id", defaultValue = "") String userId,
                       @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
                       HttpServletRequest request) {

        List<ProductCommand> productCommandList = new ArrayList<>();
        Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");

        long userIdLong;
        if (isRequestVerified) {
            userIdLong = Long.parseLong(userId);
        } else if(!tempUserId.isEmpty()){
            userIdLong = Long.parseLong(tempUserId);
        } else {
            model.addAttribute("productCommandList", productCommandList);
            return JSP_PATH_PREFIX + "cart";
        }

        Cart userCart = cartService.getCartForUser(userIdLong, !isRequestVerified);

        if (userCart != null) {
            for (Long productId : userCart.getProductIdList()) {
                StandardProduct product = (StandardProduct) productService.getProductById(productId);
                productCommandList.add(new ProductCommand(product));
            }
        }
        model.addAttribute("productCommandList", productCommandList);
        return JSP_PATH_PREFIX + "cart";
    }

    /**
     * Add string.
     *
     * @param request    the request
     * @param response   the response
     * @param userId     the user id
     * @param tempUserId the temp user id
     * @param productId  the product id
     * @return the string
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request,
                      HttpServletResponse response,
                      @CookieValue(value = "id", defaultValue = "") String userId,
                      @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
                      @RequestParam Long productId) {

        Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");

        long userIdLong;
        if (isRequestVerified) {
            userIdLong = Long.parseLong(userId);
        } else if (!tempUserId.isEmpty()) {
            userIdLong = Long.parseLong(tempUserId);
        } else {
            userIdLong = userService.getNewIdForTempUser();
            response.addCookie(createNewCookie("tempId", String.valueOf(userIdLong)));
        }

        Cart userCart = cartService.getCartForUser(userIdLong, !isRequestVerified);
        if (userCart == null) {
            userCart = cartService.createNewCart(userIdLong, !isRequestVerified);
        }
        if (productService.getProductById(productId) != null) {
            userCart.addToCart(productId);
            cartService.updateCartForUser(userIdLong, userCart);

        }

        return "redirect:view";
    }


    /**
     * Remove string.
     *
     * @param userId     the user id
     * @param tempUserId the temp user id
     * @param productId  the product id
     * @param request    the request
     * @return the string
     */
    @RequestMapping("/remove")
    public String remove(@CookieValue(value = "id", defaultValue = "") String userId,
                         @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
                         @RequestParam Long productId,
                         HttpServletRequest request) {

        Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");

        long userIdLong;
        if (isRequestVerified) {
            userIdLong = Long.parseLong(userId);
        } else if (!tempUserId.isEmpty()) {
            userIdLong = Long.parseLong(tempUserId);
        } else {
            return "redirect:view";
        }

        Cart userCart = cartService.getCartForUser(userIdLong, !isRequestVerified);
        if (userCart != null) {
            userCart.removeFromCart(productId);
            cartService.updateCartForUser(userIdLong, userCart);
        }

        return "redirect:view";
    }

    private Cookie createNewCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        return cookie;
    }
}
