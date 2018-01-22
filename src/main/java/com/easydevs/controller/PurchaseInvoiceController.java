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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibm on 2017-01-19.
 */
@Controller
@RequestMapping("/purchase-invoice")
public class PurchaseInvoiceController {

    /**
     * The constant JSP_PATH_PREFIX.
     */
    final static String JSP_PATH_PREFIX = "purchase/";

    /**
     * The Purchase invoice service.
     */
    @Autowired
    PurchaseInvoiceService purchaseInvoiceService;

    /**
     * The User service.
     */
    @Autowired
    UserService userService;

    /**
     * The Product service.
     */
    @Autowired
    ProductService productService;

    /**
     * The Cart service.
     */
    @Autowired
    CartService cartService;

    /**
     * The Email service.
     */
    @Autowired
    EmailService emailService;

    /**
     * Show create new form string.
     *
     * @param model                   the model
     * @param userId                  the user id
     * @param purchaseCreationCommand the purchase creation command
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping("/create")
    public String showCreateNewForm(Model model,
                                    @CookieValue("id") String userId,
                                    @ModelAttribute("purchaseInvoiceCreationCommand") PurchaseInvoiceCreationCommand purchaseCreationCommand) throws IOException {

        long userIdLong = Long.parseLong(userId);
        Cart userCart = cartService.getCartForUser(userIdLong, false);

        if (userCart == null || userCart.getProductIdList() == null || userCart.getProductIdList().isEmpty()) {
            return "redirect:../homepage";
        }

        PurchaseInvoice invoice = purchaseInvoiceService.createNewPurchaseInvoice();
        StandardUser user = (StandardUser) userService.getUserById(userIdLong);
        if (user != null && !user.isEmailVerified()) {
            return UserController.JSP_PATH_PREFIX + "user_email_not_verified_warn";
        }

        List<StandardProduct> productList = new ArrayList<>();
        for (Long productId : userCart.getProductIdList()) {
            StandardProduct product = (StandardProduct) productService.getProductById(productId);
            productList.add(product);
            product.setQuantity(product.getQuantity() - 1);
            product.setQuantitySold(product.getQuantitySold() + 1);
            productService.updateProduct(product);
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

    /**
     * Create string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @RequestMapping("/view-all")
    public String create(Model model,
                         @CookieValue("id") String userId) {

        model.addAttribute("purchaseInvoiceCommandList", purchaseInvoiceService.getPurchaseInvoiceListByUserId(Long.parseLong(userId)));


        return JSP_PATH_PREFIX + "purchase_all";
    }

    /**
     * View string.
     *
     * @param model     the model
     * @param invoiceId the invoice id
     * @return the string
     */
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

    /**
     * View string.
     *
     * @param invoiceId the invoice id
     * @return the string
     */
    @RequestMapping("/copy/{invoiceId}")
    public String copy(HttpServletRequest request,
                       HttpServletResponse response,
                       @CookieValue(value = "id", defaultValue = "") String userId,
                       @CookieValue(value = "tempId", defaultValue = "") String tempUserId,
                       @PathVariable Long invoiceId) {

        PurchaseInvoice invoice = purchaseInvoiceService.getPurchaseInvoiceById(invoiceId);

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

        for(int i = 0; i < invoice.getProductList().size(); i++){
            userCart.addToCart(invoice.getProductList().get(i).getId());
        }

        cartService.updateCartForUser(userIdLong, userCart);


        return "redirect:../../cart/view";
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

    private Cookie createNewCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setPath("/");
        return cookie;
    }
}
