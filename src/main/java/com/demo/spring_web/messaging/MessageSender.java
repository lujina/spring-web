package com.demo.spring_web.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.demo.spring_web.model.Order;

@Component
public class MessageSender {

	@Autowired
	JmsTemplate jmsTemplate;
	
	public void sendMessage(final Order order){
		jmsTemplate.send(new MessageCreator(){
			@Override
			public Message createMessage(Session session) throws JMSException {
				// TODO Auto-generated method stub
				return session.createObjectMessage(order);
			}
		});
	}
}
