package com.demo.spring_web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IUserService;
import com.demo.spring_web.util.ResponseFormat;

@Controller
public class UserController {
	 /** 
     * 注入userService。 
     * 如果userService继承了某个接口， 
     * 这里类型必须是接口IUserService， 
     * 不能是类UserService，不知道为什么。 
     */  
    @Autowired  
    private IUserService userService;  
    
    
    @ResponseBody
    @RequestMapping(value="/back/login", method=RequestMethod.POST)
    public Object login(User user,HttpSession session) {
        if(userService.login(user.getUsername(), user.getPassword())){
        	//获取Session 
            session.setAttribute("username", user.getUsername());
            return ResponseFormat.getResult(0, user);
        }else{
        	return ResponseFormat.getResult(1012,null);
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
