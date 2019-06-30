package com.jeffrey.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("登陆接口")
public class LoginController {

    @GetMapping("/")
    @ApiOperation("首页")
    public String home(){
        return "main/index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ApiOperation("登陆页")
    public String login(Model model){
        model.addAttribute("test","测试");
        return "main/login";
    }

    @GetMapping("/d3/p1")
    @ApiOperation("d3测试")
    public String p1(){
        return "d3/practise/p1";
    }

    @GetMapping("/test")
    @ApiOperation("adminAction测试")
    public String adminTest(Model model){
        model.addAttribute("name","test");
        return "test";
    }
}
