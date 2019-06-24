package com.jeffrey.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jeffrey.Liu
 * @date 2018-7-15
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping("/index")
    public String home(){
        return "main/index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(Model model){
        model.addAttribute("test","测试");
        return "main/login";
    }

    @GetMapping("/d3/p1")
    public String p1(){
        return "d3/practise/p1";
    }

    @GetMapping("/test")
    public String adminTest(Model model){
        model.addAttribute("name","test");
        return "test";
    }
}
