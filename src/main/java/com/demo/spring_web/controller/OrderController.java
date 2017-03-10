package com.demo.spring_web.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.spring_web.model.Order;
import com.demo.spring_web.service.IOrderService;
import com.demo.spring_web.util.ResponseFormat;

@Controller
@RequestMapping("/order")
public class OrderController {
	@Autowired
	IOrderService orderService;
	
	@ResponseBody
	@RequestMapping(value="/new", method=RequestMethod.GET)
	public Object prepareOrder(){
		return ResponseFormat.getResult(0,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/new", method=RequestMethod.POST)
	public Object sendOrder(Order order){
		orderService.sendOrder(order);
		return ResponseFormat.getResult(0,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/status", method=RequestMethod.GET)
	public Object checkOrderStatus(){
		Map<String,Order> orders = orderService.getAllOrders();
		return ResponseFormat.getResult(0,null);
	}
}
