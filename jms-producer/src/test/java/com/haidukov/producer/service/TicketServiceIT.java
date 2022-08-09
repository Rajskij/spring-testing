package com.haidukov.producer.service;

import com.haidukov.pojo.Order;
import com.haidukov.pojo.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static com.haidukov.producer.TestHelper.buildStaticTicket;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TicketServiceIT {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    private TicketService ticketService;

    @Test
    public void testSendTickets() {
        Ticket expectedTicket = buildStaticTicket();

        Order actualOrder = ticketService.createOrder(expectedTicket);

        jmsTemplate.setReceiveTimeout(10_000);

        Order receivedOrder = (Order) jmsTemplate.receiveAndConvert("order.queue");

        assertNotNull(receivedOrder);
        assertEquals(receivedOrder.getTicket().getTicketId(), actualOrder.getTicket().getTicketId());
        assertEquals(receivedOrder.getTicket().getTicketNumber(), actualOrder.getTicket().getTicketNumber());
        assertEquals(receivedOrder.getCustomer().getFullName(), actualOrder.getCustomer().getFullName());
    }
}
