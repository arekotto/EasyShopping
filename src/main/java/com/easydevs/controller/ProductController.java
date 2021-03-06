package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.command.ProductCreationCommand;
import com.easydevs.product.command.ReviewCommand;
import com.easydevs.product.command.SearchCommand;
import com.easydevs.product.model.Category;
import com.easydevs.product.model.ProductImage;
import com.easydevs.product.model.Review;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.CategoryService;
import com.easydevs.product.service.ImageService;
import com.easydevs.product.service.ProductService;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import com.easydevs.user.model.User;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arek on 02.01.2017.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    /**
     * The constant JSP_PATH_PREFIX.
     */
    final static String JSP_PATH_PREFIX = "product/";

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    /**
     * Show create new form string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model, @CookieValue("id") String userId) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
        if (user != null && !user.isEmailVerified()) {
            return UserController.JSP_PATH_PREFIX + "user_email_not_verified_warn";
        }
        List<Category> categoryCommandList = categoryService.getAll();
        model.addAttribute("productCreationCommand", new ProductCreationCommand());
        model.addAttribute("categoryCommandList", categoryCommandList);
        return JSP_PATH_PREFIX + "product_create";
    }

    /**
     * Create string.
     *
     * @param model                  the model
     * @param userId                 the user id
     * @param productCreationCommand the product creation command
     * @param request                the request
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("productCreationCommand") ProductCreationCommand productCreationCommand,
                         MultipartHttpServletRequest request) throws IOException {

        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
        if (user != null && !user.isEmailVerified()) {
            return UserController.JSP_PATH_PREFIX + "user_email_not_verified_warn";
        }

        StandardProduct newProduct = (StandardProduct) productService.createNewProduct();

        newProduct.setName(productCreationCommand.getName());
        newProduct.setDescription(productCreationCommand.getDescription());
        newProduct.setManufacturer(productCreationCommand.getManufacturer());
        newProduct.setAddedByUserId(Long.parseLong(userId));
        newProduct.setCategory(productCreationCommand.getCategory());
        newProduct.setPrice(productCreationCommand.getPrice());
        newProduct.setQuantity(productCreationCommand.getQuantity());

        MultipartFile image = request.getFile("image");

        if (!image.isEmpty()
                && (image.getContentType().equals("image/jpeg")
                || image.getContentType().equals("image/png"))) {

            saveImage(image, newProduct.getId());
            newProduct.setHasImage(true);
        }

        productService.updateProduct(newProduct);

        return "redirect:view/" + newProduct.getId();
    }

    /**
     * Show edit form string.
     *
     * @param model     the model
     * @param userId    the user id
     * @param productId the product id
     * @return the string
     */
    @RequestMapping("/edit")
    public String showEditForm(Model model, @CookieValue("id") String userId, @RequestParam Integer productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product.getAddedByUserId() == Long.parseLong(userId)) {
            ProductCommand productCommand = new ProductCommand(product);
            productCommand.setCategoryName(categoryService.getCategoryNameById(product.getCategory()));
            model.addAttribute("productCommand", productCommand);
            return JSP_PATH_PREFIX + "product_edit";
        }
        return "redirect:view/" + product.getId();
    }

    /**
     * Save string.
     *
     * @param model          the model
     * @param productId      the product id
     * @param addedByUserId  the added by user id
     * @param productCommand the product command
     * @param request        the request
     * @return the string
     * @throws IOException the io exception
     */
    @RequestMapping(value = "/save")
    public String save(Model model,
                       @RequestParam Integer productId,
                       @RequestParam Integer addedByUserId,
                       @ModelAttribute("productCommand") ProductCommand productCommand,
                       MultipartHttpServletRequest request) throws IOException {



        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product.getAddedByUserId() == addedByUserId) {
            product.setDescription(productCommand.getDescription());
            product.setManufacturer(productCommand.getManufacturer());
            product.setPrice(productCommand.getPrice());
            product.setQuantity(productCommand.getQuantity());

            MultipartFile image = request.getFile("image");

            if (!image.isEmpty()
                    && (image.getContentType().equals("image/jpeg")
                    || image.getContentType().equals("image/png"))) {

                saveImage(image, productId);
                product.setHasImage(true);

            }
            productService.updateProduct(product);

            ProductCommand productCommand1 = new ProductCommand(product);
            productCommand1.setCategoryName(categoryService.getCategoryNameById(product.getCategory()));
            model.addAttribute("productCommand", productCommand1);
        }
        return "redirect:view/" + product.getId();
    }

    private void saveImage(MultipartFile image, long productId) throws IOException {
        ProductImage productImage = new ProductImage(productId, IOUtils.toByteArray(image.getInputStream()));
        imageService.updateProductImage(productImage);
    }

    /**
     * View string.
     *
     * @param model        the model
     * @param productId    the product id
     * @param request      the request
     * @param userIdCookie the user id cookie
     * @return the string
     */
    @RequestMapping("/view/{productId}")
    public String view(Model model, @PathVariable Long productId, HttpServletRequest request,
                       @CookieValue(value = "id", defaultValue = "0") String userIdCookie) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product != null) {
            Long userId = Long.parseLong(userIdCookie);

            ProductCommand productCommand = new ProductCommand(product);
            productCommand.setCategoryName(categoryService.getCategoryNameById(product.getCategory()));

            ReviewCommand reviewCommand = new ReviewCommand();
//            reviewCommand.setUserId(Long.parseLong(userIdCookie));
            int[] ratings = new int[]{1, 2, 3, 4, 5};
            productCommand.setAverageRating(product.countAverageRating());

            Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");

            boolean isUserProduct = isRequestVerified != null && isRequestVerified && product.getAddedByUserId() == userId;
            model.addAttribute("isUserProduct", isUserProduct);
            productCommand.setShouldHideAddToCartButton(isUserProduct || product.getQuantity() <= 0);
            Boolean isReviewed = product.isReviewedByUserId(userId) || userId == 0;
            model.addAttribute("isReviewed", isReviewed);

            model.addAttribute("hasReviews", product.isHasReviews());

            model.addAttribute("productCommand", productCommand);
            model.addAttribute("reviewCommand", reviewCommand);
            model.addAttribute("ratings", ratings);

            return JSP_PATH_PREFIX + "product_view";

        }

        return "";
    }

    /**
     * Rate product string.
     *
     * @param model         the model
     * @param productId     the product id
     * @param reviewCommand the review command
     * @param userIdCookie  the user id cookie
     * @return the string
     */
    @RequestMapping(value="/review")
    public String rateProduct(Model model, Long productId, ReviewCommand reviewCommand,
                            @CookieValue(value = "id", defaultValue = "") String userIdCookie) {
        Review review = new Review();

        review.setUserId(Long.parseLong(userIdCookie));
        review.setRating(reviewCommand.getRating());
        review.setReviewText(reviewCommand.getReviewText());

        this.productService.rateProduct(productId, review);
        model.addAttribute("isReviewed", true);

        return "redirect:view/" + productId;
    }

    /**
     * View image byte [ ].
     *
     * @param model     the model
     * @param productId the product id
     * @return the byte [ ]
     */
    @ResponseBody
    @RequestMapping(value = "/image/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] viewImage(Model model, @PathVariable Long productId) {

        ProductImage image = imageService.getProductImage(productId);
        if (image != null) {
            return image.getBytes();
        }

        return new byte[0];
    }

    /**
     * Remove string.
     *
     * @param model     the model
     * @param userId    the user id
     * @param productId the product id
     * @return the string
     */
    @RequestMapping("/remove")
    public String remove(Model model, @CookieValue("id") String userId, @RequestParam Integer productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (Long.parseLong(userId) == product.getAddedByUserId()) {
            productService.removeProduct(product);
            imageService.removeProductImage(productId);
            return "redirect:user";

        }

        return "redirect:view/" + productId;
    }

    /**
     * View users string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @RequestMapping("/user")
    public String viewUsers(Model model, @CookieValue("id") String userId) {
        List<StandardProduct> standardProducts = productService.getProductsByUserId(Long.parseLong(userId));
        List<ProductCommand> productCommandList = new ArrayList<>();
        for (StandardProduct standardProduct : standardProducts) {
            ProductCommand productCommand = new ProductCommand(standardProduct);
            productCommand.setCategoryName(categoryService.getCategoryNameById(standardProduct.getCategory()));
            productCommand.setAverageRating(standardProduct.countAverageRating());
            productCommandList.add(productCommand);
        }
        model.addAttribute("productCommandList", productCommandList);
        model.addAttribute("isOnlyForUser", true);
        return JSP_PATH_PREFIX + "product_all";
    }

    /**
     * View all string.
     *
     * @param model  the model
     * @param userId the user id
     * @return the string
     */
    @RequestMapping("/all")
    public String viewAll(Model model, @CookieValue(value = "id", defaultValue = "") String userId) {
        if (!model.containsAttribute("productCommandList")) {
            List<StandardProduct> standardProducts = productService.getAll();
            List<ProductCommand> productCommandList = new ArrayList<>();
            for (StandardProduct standardProduct : standardProducts) {
                ProductCommand productCommand = new ProductCommand(standardProduct);
                productCommand.setCategoryName(categoryService.getCategoryNameById(standardProduct.getCategory()));
                if (userId.equals(String.valueOf(productCommand.getCreatedByUserId())) || productCommand.getQuantity() <= 0) {
                    productCommand.setShouldHideAddToCartButton(true);
                }
                productCommand.setAverageRating(standardProduct.countAverageRating());
                productCommandList.add(productCommand);
            }
            model.addAttribute("productCommandList", productCommandList);

            model.addAttribute("isOnlyForUser", false);
        }
        if (!model.containsAttribute("searchCommand")) {
            model.addAttribute("searchCommand", new SearchCommand());
        }
        List<Category> categoryCommandList = categoryService.getAll();
        model.addAttribute("categoryCommandList", categoryCommandList);

        return JSP_PATH_PREFIX + "product_all";
    }

    /**
     * View by category string.
     *
     * @param model      the model
     * @param categoryId the category id
     * @return the string
     */
    @RequestMapping("/{category}")
    public String viewByCategory(Model model, @PathVariable long categoryId) {
        List<StandardProduct> standardProducts = productService.getProductsByCategory(categoryId);
        List<ProductCommand> productCommandList = new ArrayList<>();

        for (StandardProduct standardProduct: standardProducts) {
            productCommandList.add(new ProductCommand(standardProduct));
        }

        model.addAttribute("productCommandList", productCommandList);
        return JSP_PATH_PREFIX + "product_all";
    }

    /**
     * Search string.
     *
     * @param searchCommand      the search command
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping("/search")
    public String search(@ModelAttribute("searchCommand") SearchCommand searchCommand,
                         RedirectAttributes redirectAttributes) {

        List<StandardProduct> productList;
        Integer searchedCategoryId = searchCommand.getSearchCategory();
        if (searchedCategoryId != null && searchedCategoryId != -1) {
            productList = productService.search(searchCommand.getSearchedPhrase(), searchedCategoryId);
        } else {
            productList = productService.search(searchCommand.getSearchedPhrase());
        }
        List<ProductCommand> productCommandList = new ArrayList<>();
        for (StandardProduct standardProduct : productList) {
            ProductCommand productCommand = new ProductCommand(standardProduct);
            productCommand.setAverageRating(standardProduct.countAverageRating());
            productCommand.setCategoryName(categoryService.getCategoryNameById(standardProduct.getCategory()));
            productCommandList.add(productCommand);
        }
        redirectAttributes.addFlashAttribute("productCommandList", productCommandList);
        redirectAttributes.addFlashAttribute("isOnlyForUser", false);
        redirectAttributes.addFlashAttribute("searchCommand", searchCommand);

        return "redirect:all" ;
    }

}
