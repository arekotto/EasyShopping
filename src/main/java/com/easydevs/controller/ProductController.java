package com.easydevs.controller;

import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.command.ProductCreationCommand;
import com.easydevs.product.model.Category;
import com.easydevs.product.model.ProductImage;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.product.service.CategoryService;
import com.easydevs.product.service.ImageService;
import com.easydevs.product.service.ProductService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

    @RequestMapping("/createForm")
    public String showCreateNewForm(Model model) {
        model.addAttribute("productCreationCommand", new ProductCreationCommand());
        return "product_create";
    }

    @RequestMapping("/create")
    public String create(Model model,
                         @CookieValue("id") String userId,
                         @ModelAttribute("productCreationCommand") ProductCreationCommand productCreationCommand,
                         MultipartHttpServletRequest request) throws IOException {



        StandardProduct newProduct = (StandardProduct) productService.createNewProduct();
        List<Category> categoryList = categoryService.getAll();

        newProduct.setName(productCreationCommand.getName());
        newProduct.setDescription(productCreationCommand.getDescription());
        newProduct.setManufacturer(productCreationCommand.getManufacturer());
        newProduct.setAddedByUserId(Long.parseLong(userId));
        newProduct.setCategory(productCreationCommand.getCategory());
        newProduct.setPrice(productCreationCommand.getPrice());
        productService.updateProduct(newProduct);

        MultipartFile image = request.getFile("image");

        if (!image.isEmpty()
                && (image.getContentType().equals("image/jpeg")
                || image.getContentType().equals("image/png"))) {

            saveImage(image, newProduct.getId());
        }

        return "redirect:view/" + newProduct.getId();
    }

    @RequestMapping("/edit")
    public String showEditForm(Model model, @CookieValue("id") String userId, @RequestParam Integer productId) {

        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product.getAddedByUserId() == Long.parseLong(userId)) {
            model.addAttribute("productCommand", new ProductCommand(product));
            return "product_edit";
        }
        return "redirect:view/" + product.getId();
    }

    @RequestMapping(value = "/save")
    public String save(Model model,
                       @CookieValue("id") String userId,
                       @RequestParam Integer productId,
                       @RequestParam Integer addedByUserId,
                       @ModelAttribute("productCommand") ProductCommand productCommand,
                       MultipartHttpServletRequest request) throws IOException {



        StandardProduct product = (StandardProduct) productService.getProductById(productId);
        if (product.getAddedByUserId() == addedByUserId) {
            product.setDescription(productCommand.getDescription());
            product.setManufacturer(productCommand.getManufacturer());
            product.setPrice(productCommand.getPrice());
            productService.updateProduct(product);

            MultipartFile image = request.getFile("image");

            if (!image.isEmpty()
                    && (image.getContentType().equals("image/jpeg")
                    || image.getContentType().equals("image/png"))) {

                saveImage(image, productId);
            }

            model.addAttribute("productCommand", new ProductCommand(product));
        }
        return "redirect:view/" + product.getId();
    }

    private void saveImage(MultipartFile image, long productId) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());

        ProductImage productImage = new ProductImage(productId, IOUtils.toByteArray(image.getInputStream()));
        imageService.updateProductImage(productImage);
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
