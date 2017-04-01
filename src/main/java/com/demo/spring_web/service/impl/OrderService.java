package com.demo.spring_web.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring_web.messaging.MessageSender;
import com.demo.spring_web.model.InventoryResponse;
import com.demo.spring_web.model.Order;
import com.demo.spring_web.model.OrderStatus;
import com.demo.spring_web.service.IOrderService;
import com.demo.spring_web.service.tmp.IOrderRepository;
import com.demo.spring_web.util.StringUtils;

@Service
public class OrderService implements IOrderService {
	static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
	@Autowired
    IOrderRepository orderRepository;
	
	@Autowired
	MessageSender messageSender;
	
	@Override
	public void updateOrder(InventoryResponse response) {
		// TODO Auto-generated method stub
		Order order = orderRepository.getOrder(response.getOrderId());
		if(response.getReturnCode() == 200){
			order.setStatus(OrderStatus.CONFIRMED);
		}else if(response.getReturnCode() == 300){
			order.setStatus(OrderStatus.FAILED);
		}else{
			order.setStatus(OrderStatus.PENDING);
		}
	}

	@Override
	public void sendOrder(Order order) {
		// TODO Auto-generated method stub
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		order.setId(StringUtils.getRandomUUIDStr());
		order.setStatus(OrderStatus.CREATED);
		orderRepository.putOrder(order);
		LOG.info("Application : sending order request {}", order);
		messageSender.sendMessage(order);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
	}

	@Override
	public Map<String,Order> getAllOrders() {
		// TODO Auto-generated method stub
		return orderRepository.getAllOrders();
	}

}
