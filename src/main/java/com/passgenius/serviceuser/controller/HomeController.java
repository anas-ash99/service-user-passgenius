package com.passgenius.serviceuser.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/users")
public class HomeController {

    @GetMapping("/")
    public String welccome(){
        return "welcome homee";
    }
    @GetMapping("/home")
    public String welccome2(){
        return "welcome homee 2";
    }
}
