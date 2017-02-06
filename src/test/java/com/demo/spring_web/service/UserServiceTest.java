package com.demo.spring_web.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;  
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.demo.spring_web.controller.UserController; 

public class UserServiceTest {
	@Test  
	public void test() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
		UserController controller = ctx.getBean(UserController.class);
		controller.registe();
	}
}
