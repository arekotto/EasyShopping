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

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model, @CookieValue("id") String userId) {
        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
        if (user != null && !user.isEmailVerified()) {
            return "user_email_not_verified_warn";
        }
        List<Category> categoryCommandList = categoryService.getAll();
        model.addAttribute("productCreationCommand", new ProductCreationCommand());
        model.addAttribute("categoryCommandList", categoryCommandList);
        return "product_create";
    }

    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("productCreationCommand") ProductCreationCommand productCreationCommand,
                         MultipartHttpServletRequest request) throws IOException {

        StandardUser user = (StandardUser) userService.getUserById(Long.parseLong(userId));
        if (user != null && !user.isEmailVerified()) {
            return "user_email_not_verified_warn";
        }

        StandardProduct newProduct = (StandardProduct) productService.createNewProduct();

        newProduct.setName(productCreationCommand.getName());
        newProduct.setDescription(productCreationCommand.getDescription());
        newProduct.setManufacturer(productCreationCommand.getManufacturer());
        newProduct.setAddedByUserId(Long.parseLong(userId));
        newProduct.setCategory(productCreationCommand.getCategory());
        newProduct.setPrice(productCreationCommand.getPrice());

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

    @RequestMapping("/edit")
    public String showEditForm(Model model, @CookieValue("id") String userId, @RequestParam Integer productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product.getAddedByUserId() == Long.parseLong(userId)) {
            ProductCommand productCommand = new ProductCommand(product);
            productCommand.setCategoryName(categoryService.getCategoryNameById(product.getCategory()));
            model.addAttribute("productCommand", productCommand);
            return "product_edit";
        }
        return "redirect:view/" + product.getId();
    }

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

    @RequestMapping("/view/{productId}")
    public String view(Model model, @PathVariable Long productId, HttpServletRequest request,
                       @CookieValue(value = "id", defaultValue = "") String userIdCookie) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product != null) {
            Long userId = Long.parseLong(userIdCookie);

            ProductCommand productCommand = new ProductCommand(product);
            productCommand.setCategoryName(categoryService.getCategoryNameById(product.getCategory()));

            ReviewCommand reviewCommand = new ReviewCommand();
//            reviewCommand.setUserId(Long.parseLong(userIdCookie));
            int[] ratings = new int[]{1, 2, 3, 4, 5};
            productCommand.setAverageRating(product.countAverageRating());
            model.addAttribute("productCommand", productCommand);
            model.addAttribute("reviewCommand", reviewCommand);
            model.addAttribute("ratings", ratings);

            Boolean isRequestVerified = (Boolean) request.getAttribute("isRequestVerified");
            if (isRequestVerified != null && isRequestVerified && product.getAddedByUserId() == userId) {
                model.addAttribute("isUserProduct", true);
            } else {
                model.addAttribute("isUserProduct", false);
            }

            Boolean isReviewedByUser = product.isReviewedByUserId(userId);
            if (isReviewedByUser) {
                model.addAttribute("isReviewed", true);
            } else {
                model.addAttribute("isReviewed", false);
            }

            boolean isHasReviews = product.isHasReviews();
            if (isHasReviews) {
                model.addAttribute("hasReviews", true);
            } else {
                model.addAttribute("hasReviews", false);
            }

            return "product_view";

        }

        return "";
    }

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

    @ResponseBody
    @RequestMapping(value = "/image/{productId}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] viewImage(Model model, @PathVariable Long productId) {

        ProductImage image = imageService.getProductImage(productId);
        if (image != null) {
            return image.getBytes();
        }

        return new byte[0];
    }

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
        return "product_all";
    }

    @RequestMapping("/all")
    public String viewAll(Model model, @CookieValue(value = "id", defaultValue = "") String userId) {
        if (!model.containsAttribute("productCommandList")) {
            List<StandardProduct> standardProducts = productService.getAll();
            List<ProductCommand> productCommandList = new ArrayList<>();
            for (StandardProduct standardProduct : standardProducts) {
                ProductCommand productCommand = new ProductCommand(standardProduct);
                productCommand.setCategoryName(categoryService.getCategoryNameById(standardProduct.getCategory()));
                if (userId.equals(String.valueOf(productCommand.getCreatedByUserId()))) {
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
