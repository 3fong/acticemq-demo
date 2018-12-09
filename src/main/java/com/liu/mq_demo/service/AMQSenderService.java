package com.liu.mq_demo.service;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class AMQSenderService {

    @Resource(name = "jmsTemplate")
    private JmsTemplate jmsTemplate;

    @Resource(name = "destinationQueue")
    private Destination destination;

    //向特定的队列发送消息
    public void sendMsg(final Object obj) {
        try {
            jmsTemplate.send(destination, new MessageCreator() {

				public Message createMessage(Session arg0) throws JMSException {
					return arg0.createTextMessage(String.valueOf(obj));
				}
                
            });

        } catch (Exception ex) {
        }
    }
    
	public static void main(String[] args) throws JMSException {
		ApplicationContext context = new AnnotationConfigApplicationContext(MQConf.class);
		AMQSenderService sender = context.getBean(AMQSenderService.class);
		TextMessage msg = new ActiveMQTextMessage();
		msg.setText("hello world");
		sender.sendMsg(msg);
	}
}