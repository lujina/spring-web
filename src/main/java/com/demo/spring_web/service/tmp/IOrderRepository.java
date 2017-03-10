package com.demo.spring_web.service.tmp;

import java.util.Map;

import com.demo.spring_web.model.Order;

public interface IOrderRepository {
	public void putOrder(Order order);
    
    public Order getOrder(String orderId);
     
    public Map<String, Order> getAllOrders();
}
