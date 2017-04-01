package com.demo.spring_web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.spring_web.messaging.MessageSender;
import com.demo.spring_web.model.InventoryResponse;
import com.demo.spring_web.model.Order;
import com.demo.spring_web.service.IOrderInventoryService;

@Service
public class OrederInventoryService implements IOrderInventoryService {
	static final Logger LOG = LoggerFactory.getLogger(OrederInventoryService.class);
	@Autowired
	MessageSender messageSender;
	
	@Override
	public void processOrder(Order order) {
		// TODO Auto-generated method stub
		
		//这里可以执行其它关于订单的业务逻辑
		InventoryResponse response = prepareResponse(order);
		LOG.info("Inventory : sending order confirmation {}", response);
		messageSender.sendMessage2(response);
	}

	private InventoryResponse prepareResponse(Order order){
		InventoryResponse response = new InventoryResponse();
		response.setOrderId(order.getId());
		response.setReturnCode(200);
		response.setComment("Order Processed successfully");
		return response;
	}
}
