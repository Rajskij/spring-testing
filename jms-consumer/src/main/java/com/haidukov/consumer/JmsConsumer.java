package com.haidukov.consumer;


import com.haidukov.consumer.service.TicketService;
import com.haidukov.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import static com.haidukov.consumer.config.JmsConfig.ORDER_QUEUE;

@Component
public class JmsConsumer {
    private final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    private TicketService ticketService;

    @JmsListener(destination = ORDER_QUEUE)
    public void receiveTickets(Order order) {
        LOGGER.info("Received order: {}", order);
        ticketService.saveTicket(order);
    }
}
