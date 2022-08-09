package com.haidukov.producer.service;

import com.haidukov.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import static com.haidukov.producer.TestHelper.buildStaticOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class JmsProducerIT {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsProducer jmsProducer;

    @Test
    public void testSendTickets() {
        Order expectedOrder = buildStaticOrder();

        jmsProducer.sendMessage("order.queue", expectedOrder);

        jmsTemplate.setReceiveTimeout(10_000);

        Order receivedOrder = (Order) jmsTemplate.receiveAndConvert("order.queue");

        assertNotNull(receivedOrder);
        assertEquals(receivedOrder.getTicket().getTicketId(), expectedOrder.getTicket().getTicketId());
        assertEquals(receivedOrder.getTicket().getTicketNumber(), expectedOrder.getTicket().getTicketNumber());
        assertEquals(receivedOrder.getCustomer().getFullName(), expectedOrder.getCustomer().getFullName());
    }
}
