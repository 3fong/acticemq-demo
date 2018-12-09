package com.liu.mq_demo.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyMessageListener implements MessageListener {

	public void onMessage(Message msg) {
		if (msg instanceof TextMessage) {
			try {
				TextMessage txtMsg = (TextMessage) msg;
				String message = txtMsg.getText();
				System.out.println("receive txt msg===" + message);
			} catch (JMSException e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new IllegalArgumentException("Message must be of type TextMessage");
		}
	}

}
