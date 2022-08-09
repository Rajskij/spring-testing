package com.haidukov.producer.service;

import com.haidukov.pojo.Customer;
import com.haidukov.pojo.Order;
import com.haidukov.pojo.Ticket;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.haidukov.producer.config.JmsConfig.ORDER_QUEUE;

@Service
public class TicketService {

    private final JmsProducer jmsProducer;

    public TicketService(JmsProducer jmsProducer) {
        this.jmsProducer = jmsProducer;
    }

    public Order createOrder(Ticket ticket) {
        if (ticket.getTicketId() == null) {
            ticket.setTicketId(uuid());
        }

        Order order = buildOrder(ticket);
        jmsProducer.sendMessage(ORDER_QUEUE, order);

        return order;
    }

    private Order buildOrder(Ticket ticket) {
        Customer customer = new Customer(uuid(), "Default Customer");
        return new Order(uuid(), ticket, customer);
    }

    private static String uuid() {
        return UUID.randomUUID().toString();
    }
}
