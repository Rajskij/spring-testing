package com.haidukov.producer.service;

import com.haidukov.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsProducer {
    private final Logger LOGGER = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendMessage(String destination, Order order){
        LOGGER.info("Send order from customer: {}, Ticket: {} pieces",
                order.getCustomer().getFullName(),
                order.getTicket().getTicketNumber());

        jmsTemplate.convertAndSend(destination, order, message -> {
            message.setStringProperty("orderId", order.getOrderId());
            message.setStringProperty("customerFullName", order.getCustomer().getFullName());
            message.setIntProperty("ItemNumber", order.getTicket().getTicketNumber());
            return message;
        });
    }

}
