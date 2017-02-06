package com.demo.spring_web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	 /** 
     * 注入userService。 
     * 如果userService继承了某个接口， 
     * 这里类型必须是接口IUserService， 
     * 不能是类UserService，不知道为什么。 
     */  
    @Autowired  
    private IUserService userService;  
      
    @RequestMapping(value="/registe", method=RequestMethod.GET)  
    public String registe() {  
        User user = new User(0, "小马云", "999");  
        userService.registe(user);  
        return "index";  
    }  
}
