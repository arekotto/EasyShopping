package com.easydevs.controller;

import com.easydevs.news.News;
import com.easydevs.news.NewsService;
import com.easydevs.product.command.ProductCommand;
import com.easydevs.product.model.StandardProduct;
import com.easydevs.purchase.model.Cart;
import com.easydevs.user.UserService;
import com.easydevs.user.model.StandardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrzej on 2017-02-01.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/main")
    public String adminMain(Model model, @CookieValue("id") String userId) {

        return "admin/admin_main";
    }

    @RequestMapping("/deleteuser")
    public String deleteUser(Model model, @CookieValue("id") String userId) {
        List<StandardUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/admin_delete_user";
    }

    @RequestMapping("/remove")
    public String removeUser(Model model, @RequestParam("remove") long userId) {
        userService.removeUser(userId);
        return "redirect:deleteuser";
    }

    @RequestMapping("/addnews")
    public String addNews(Model model) {
        News newsCommand = new News();
        model.addAttribute("newsCommand", newsCommand);
        return "admin/admin_add_news";
    }

    @RequestMapping("/createnews")
    public String createNews(Model model, @ModelAttribute ("newsCommand") News newNews) {
        newsService.insertNews(newNews);
        return "redirect:main";
    }
}