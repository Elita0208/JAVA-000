package com.example.demo.controller;

import com.example.demo.annotation.RoutingWith;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class MyController {
    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @RoutingWith("slaveDataSource")
    public String index() {
        return "aaa";
    }

}