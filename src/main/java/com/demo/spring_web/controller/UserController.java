package com.demo.spring_web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;

@Controller
@RequestMapping(value="/")
public class UserController {
	 /** 
     * 注入userService。 
     * 如果userService继承了某个接口， 
     * 这里类型必须是接口IUserService， 
     * 不能是类UserService，不知道为什么。 
     */  
    @Autowired  
    private IUserService userService;  
    
    @RequestMapping(value="/back/login", method=RequestMethod.POST)  
    public String login(HttpServletRequest http) {  
        String username = http.getParameter("username");
        String password = http.getParameter("password");
        if(userService.login(username, password)){
        	//获取Session  
            HttpSession session = http.getSession();  
            session.setAttribute("username", username);
            return "redirect:/home";
        }else{
        	return "redirect:/login.html";
        }
        
    }
    
    @RequestMapping(value="/back/registe", method=RequestMethod.POST)  
    public String registe() {  
        User user = new User(0, "小马云", "999");  
        userService.registe(user);  
        return "redirect:/home";  
    }
    
    @RequestMapping(value="/home", method=RequestMethod.GET)  
    public String home() { 
        return "home";  
    }
    
}
