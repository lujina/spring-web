package com.demo.spring_web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.demo.spring_web.model.FileDto;
import com.demo.spring_web.model.User;
import com.demo.spring_web.service.IFileService;
import com.demo.spring_web.service.IUserService;
import com.demo.spring_web.util.ResponseFormat;

@Controller
public class UserController {
    @Autowired  
    private IUserService userService; 
    
    @Autowired
    private IFileService fileService;
    
//    @ResponseBody
//    @RequestMapping(value="/back/login", method=RequestMethod.POST)
//    public Object login(User user,HttpSession session) {
//        if(userService.login(user.getUsername(), user.getPassword())){
//        	//获取Session 
//            session.setAttribute("username", user.getUsername());
//            return ResponseFormat.getResult(0, user);
//        }else{
//        	return ResponseFormat.getResult(1012,null);
//        }
//        
//    }
    
    @ResponseBody
    @RequestMapping(value="/back/user/info",method=RequestMethod.GET)
    public Object queryInfo(HttpServletRequest request) {
    	User user = userService.findByName(getPrincipal());
    	return ResponseFormat.getResult(0, user);
        
    }
    
    @ResponseBody
    @RequestMapping(value="/back/user/update", method=RequestMethod.POST)
    public Object update(User u2) {
    	User u1 = userService.findByName(getPrincipal());
    	u1.setEmail(u2.getEmail());
    	if(u2.getPassword() != null){
    		u1.setPassword(u2.getPassword());
    	};
    	userService.update(u1);
    	return ResponseFormat.getResult(0,u1);        
    }
    
    @ResponseBody
    @RequestMapping(value="/back/user/uploadImg", method=RequestMethod.POST)
    public Object saveImg(@RequestParam(value = "userImg", required = false)MultipartFile img,
    		@RequestParam(value = "userId", required = false) Integer id) {
    	FileDto fileDto = null;
    	try {
			 fileDto = fileService.uploadFile(img);
			 User user = userService.findByName(getPrincipal());
			 user.setUserimg(fileDto.getFileFullUrl());
			 userService.update(user);
			 return ResponseFormat.getResult(0,fileDto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseFormat.getResult(1000,null);  
		}
    	      
    }
    
    @ResponseBody
    @RequestMapping(value="/login/success",method=RequestMethod.GET)
    public Object loginSucc(HttpServletRequest request) {
    	return ResponseFormat.getResult(0, null);
        
    }
    
    @ResponseBody
    @RequestMapping(value="/login/error",method=RequestMethod.GET)
    public Object loginFail(HttpServletRequest request) {
    	return ResponseFormat.getResult(1012,null);
        
    }
    
    @ResponseBody
    @RequestMapping(value="/back/registe", method=RequestMethod.POST)  
    public Object registe(User user) {
        userService.registe(user);  
        return ResponseFormat.getResult(0, user);  
    }
    
    @RequestMapping(value="/home", method=RequestMethod.GET)  
    public String home(HttpSession session) {
    	session.setAttribute("username", getPrincipal());
        return "home";  
    }
  
    
    /**
     * This method handles Access-Denied redirect.
     * 没有权限，访问被拒绝
     * 在前端重新发出加载403页面的请求
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage() {
    	return "redirect:/403.html";
    }
    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal(){
        String username = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
