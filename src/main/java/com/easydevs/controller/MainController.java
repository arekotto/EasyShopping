package com.easydevs.controller;

import com.easydevs.auth.AuthenticationService;
import com.easydevs.news.News;
import com.easydevs.news.NewsService;
import com.easydevs.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by arekotto on 29/12/2016.
 */
@Controller
public class MainController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @Autowired
    NewsService newsService;

    @RequestMapping("")
    public String showHomePage(Model model) {
        List<News> newsCommandList = newsService.findAll();
        model.addAttribute("newsCommandList", newsCommandList);
        return "home";
    }
}
