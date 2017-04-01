package com.demo.spring_web.messaging;

import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import com.demo.spring_web.model.InventoryResponse;
import com.demo.spring_web.model.Order;
import com.demo.spring_web.service.IOrderInventoryService;
import com.demo.spring_web.service.IOrderService;

@Component
public class MessageReceiver {
	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
	private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
	private static final String ORDER_QUEUE = "order-queue";

	@Autowired
	IOrderService orderService;
	@Autowired
	IOrderInventoryService orderInventoryService;
	
	//接收订单仓库处理完后的订单消息
	@JmsListener(destination = ORDER_RESPONSE_QUEUE)
	public void receiveMessage(final Message<InventoryResponse> message) throws JMSException {
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		MessageHeaders headers = message.getHeaders();
		LOG.info("Application : headers received : {}", headers);

		InventoryResponse response = message.getPayload();
		LOG.info("Application : response received : {}", response);

		orderService.updateOrder(response);
		LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
    //接收新增的订单消息，并在仓库处理完订单后，发送已处理的订单消息
	@JmsListener(destination = ORDER_QUEUE)
	public void receiveMessage2(final Message<Order> message) throws JMSException {
		LOG.info("----------------------------------------------------");
		MessageHeaders headers = message.getHeaders();
		LOG.info("Application : headers received : {}", headers);

		Order order = message.getPayload();
		LOG.info("Application : product : {}", order);

		orderInventoryService.processOrder(order);
		LOG.info("----------------------------------------------------");
	}

}
