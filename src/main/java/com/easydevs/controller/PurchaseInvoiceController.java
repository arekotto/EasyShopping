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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibm on 2017-01-19.
 */
@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    @Autowired
    PurchaseInvoiceService purchaseInvoiceService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model,
                                    @CookieValue("id") String userId,
                                    @ModelAttribute("purchaseInvoiceCreationCommand") PurchaseInvoiceCreationCommand purchaseCreationCommand) {
        model.addAttribute("purchaseInvoiceCreationCommand", new PurchaseInvoiceCreationCommand());

        Cart userCart = cartService.getCartForUser(Long.parseLong(userId));
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
        List<StandardProduct> productCommandList = new ArrayList<>();

        if (userCart != null) {
            for (Long productId : userCart.getProductIdList()) {
                StandardProduct product = (StandardProduct) productService.getProductById(productId);
                productCommandList.add(product);

            }
        }

        PurchaseInvoice invoice = (PurchaseInvoice) purchaseInvoiceService.createNewPurchaseInvoice();

        invoice.setShipToAddressCity(user.getCity());
        invoice.setShipToAddressCountry(user.getCountry());
        invoice.setShipToAddressStreet(user.getStreet());
        invoice.setUserId(user.getId());
        invoice.setUserName(user.getName());
        invoice.setProductList(productCommandList);
        invoice.setPrice(invoice.getTotalPrice());

        model.addAttribute("productCommandList", productCommandList);

        return "purchase_create";
    }

    @RequestMapping("/create/{purchaseInvoiceId}")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @PathVariable Long purchaseInvoiceId,
                         @ModelAttribute("purchaseInvoiceCreationCommand") PurchaseInvoiceCreationCommand purchaseCreationCommand) {

        PurchaseInvoice invoice  = purchaseInvoiceService.getPurchaseInvoiceById(purchaseInvoiceId);

//        Cart userCart = cartService.getCartForUser(Long.parseLong(userId));
//        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
//        List<StandardProduct> productCommandList = new ArrayList<>();
//
//        if (userCart != null) {
//            for (Long productId : userCart.getProductIdList()) {
//                StandardProduct product = (StandardProduct) productService.getProductById(productId);
//                productCommandList.add(product);
//
//            }
//        }
//
//        PurchaseInvoice invoice = (PurchaseInvoice) purchaseInvoiceService.createNewPurchaseInvoice();
//
        invoice.setShipToAddressCity(purchaseCreationCommand.getShipToAddressCity());
        invoice.setShipToAddressCountry(purchaseCreationCommand.getShipToAddressCountry());
        invoice.setShipToAddressStreet(purchaseCreationCommand.getShipToAddressStreet());
//        invoice.setPrice(invoice.getTotalPrice());
//
//        model.addAttribute("productCommandList", productCommandList);

        return "redirect:view/" + invoice.getId();
    }

    @RequestMapping("/view/invoiceId")
    public String view(Model model, @PathVariable Long invoiceId) {

        PurchaseInvoice invoice = purchaseInvoiceService.getPurchaseInvoiceById(invoiceId);
        if (invoice != null) {
            model.addAttribute("purchaseInvoiceCommand", new PurchaseInvoiceCommand(invoice));
            return "invoice_view";

        }

        return "";
    }

}
