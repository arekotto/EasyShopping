package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.ProductService;
import com.easydevs.purchase.command.PurchaseInvoiceCommand;
import com.easydevs.purchase.command.PurchaseInvoiceCreationCommand;
import com.easydevs.purchase.model.Cart;
import com.easydevs.purchase.model.PurchaseInvoice;
import com.easydevs.purchase.service.CartService;
import com.easydevs.purchase.service.PurchaseInvoiceService;
import com.easydevs.user.EmailService;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibm on 2017-01-19.
 */
@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    final static String JSP_PATH_PREFIX = "purchase/";

    @Autowired
    PurchaseInvoiceService purchaseInvoiceService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    EmailService emailService;

    @RequestMapping("/create")
    public String showCreateNewForm(Model model,
                                    @CookieValue("id") String userId,
                                    @ModelAttribute("purchaseInvoiceCreationCommand") PurchaseInvoiceCreationCommand purchaseCreationCommand) throws IOException {

        long userIdLong = Long.parseLong(userId);
        Cart userCart = cartService.getCartForUser(userIdLong, false);

        if (userCart != null && userCart.getProductIdList() != null && !userCart.getProductIdList().isEmpty()) {
            PurchaseInvoice invoice = purchaseInvoiceService.createNewPurchaseInvoice();
            StandardUser user = (StandardUser) userService.getUserById(userIdLong);
            if (user != null && !user.isEmailVerified()) {
                return UserController.JSP_PATH_PREFIX + "user_email_not_verified_warn";
            }
            List<StandardProduct> productList = new ArrayList<>();

            for (Long productId : userCart.getProductIdList()) {
                StandardProduct product = (StandardProduct) productService.getProductById(productId);
                productList.add(product);

            }
            invoice.setShipToAddressCity(user.getCity());
            invoice.setShipToAddressCountry(user.getCountry());
            invoice.setShipToAddressStreet(user.getStreet());
            invoice.setUserId(user.getId());
            invoice.setUserName(user.getName());
            invoice.setProductList(productList);
            invoice.calculateCurrentPrice();

            model.addAttribute("purchaseInvoiceCommand", invoice);

            purchaseInvoiceService.updatePurchaseInvoice(invoice);

            userCart.resetCart();
            cartService.updateCartForUser(userIdLong, userCart);

            emailService.sendEmail(user.getEmail(), "Purchase Confirmation", getPurchaseEmailBody(invoice ,user.getName()));

            return "redirect:view/" + invoice.getId();

        }



        return "redirect:../homepage";
    }

    @RequestMapping("/view-all")
    public String create(Model model,
                         @CookieValue("id") String userId) {

        model.addAttribute("purchaseInvoiceCommandList", purchaseInvoiceService.getPurchaseInvoiceListByUserId(Long.parseLong(userId)));


        return JSP_PATH_PREFIX + "purchase_all";
    }

    @RequestMapping("/view/{invoiceId}")
    public String view(Model model, @PathVariable Long invoiceId) {

        PurchaseInvoice invoice = purchaseInvoiceService.getPurchaseInvoiceById(invoiceId);

        if (invoice != null) {
            invoice.calculateCurrentPrice();
            model.addAttribute("purchaseInvoiceCommand", invoice);
            return JSP_PATH_PREFIX + "purchase_view";
        }

        return "";
    }

    private String getPurchaseEmailBody(PurchaseInvoice invoice, String userName) {

        String body;

        body = "Hello " + userName + "!\n\n";
        body = body.concat("This is a confirmation email of your purchase from the EasyShopping store. Below are the items you have acquired:\n\n");

        for (StandardProduct standardProduct : invoice.getProductList()) {
            body = body.concat(standardProduct.getDescriptionForEmail());
        }

        body = body.concat("\n\nThank you for shopping with us!\n\nThe EazyDevs Team\n");
        System.out.println(body);
        return body;
    }
}
