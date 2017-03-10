package com.demo.spring_web.service.tmp;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.demo.spring_web.model.Order;

@Service
public class OrderRepository implements IOrderRepository{
	private final Map<String,Order> orders = new ConcurrentHashMap<String, Order>();
	@Override
	public void putOrder(Order order) {
		// TODO Auto-generated method stub
		orders.put(order.getId(), order);
	}

	@Override
	public Order getOrder(String orderId) {
		// TODO Auto-generated method stub
		return orders.get(orderId);
	}

	@Override
	public Map<String, Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orders;
	}

}
