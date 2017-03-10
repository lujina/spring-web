package com.demo.spring_web.service;

import java.util.Map;

import com.demo.spring_web.model.InventoryResponse;
import com.demo.spring_web.model.Order;

public interface IOrderService {
	public void updateOrder(InventoryResponse reponse);
	public void sendOrder(Order order);
	public Map<String,Order> getAllOrders(); 
}
