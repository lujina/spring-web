package com.demo.spring_web.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.demo.spring_web.model.InventoryResponse;
import com.demo.spring_web.model.Order;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;
	private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";
	private static final String ORDER_QUEUE = "order-queue";
	
	//新增一个订单
	public void sendMessage(final Order order){
		jmsTemplate.send(ORDER_QUEUE,new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(order);
			}
		});
	}
	
	//仓库处理完一个订单
	public void sendMessage2(final InventoryResponse inventoryResponse){
		jmsTemplate.send(ORDER_RESPONSE_QUEUE,new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(inventoryResponse);
			}
		});
	}
}
