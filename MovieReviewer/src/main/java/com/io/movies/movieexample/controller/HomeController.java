package com.io.movies.movieexample.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")

public class HomeController {


    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }



    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String register() {
        return "register";
    }

    @GetMapping(value = "/logout")
    public String getLogoutPage(Model model) {
                return "logout";
    }


    }

