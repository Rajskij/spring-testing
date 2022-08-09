package com.haidukov.consumer;

import com.haidukov.consumer.entity.TicketOrder;
import com.haidukov.consumer.repository.TicketOrderRepository;
import com.haidukov.pojo.Order;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.haidukov.consumer.TestHelper.buildStaticOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class JmsConsumerIT {

    @Autowired
    private JmsConsumer jmsConsumer;

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Test
    public void testReceiveTickets() {
        Order expectedOrder = buildStaticOrder();

        jmsConsumer.receiveTickets(expectedOrder);

        TicketOrder actualTicketOrder = ticketOrderRepository.findAll().stream()
                .filter(ticketOrder -> ticketOrder.getTicketId().equals(expectedOrder.getTicket().getTicketId()))
                .findFirst()
                .orElse(null);

        assertNotNull(actualTicketOrder);
        assertEquals(actualTicketOrder.getTicketId(), expectedOrder.getTicket().getTicketId());
        assertEquals(actualTicketOrder.getTicketNumber(), expectedOrder.getTicket().getTicketNumber());
        assertEquals(actualTicketOrder.getCustomerName(), expectedOrder.getCustomer().getFullName());
    }

    @Test
    public void testReceiveTicketsAsync() {
        TestHelper testHelper = new TestHelper();
        Order firstOrder = testHelper.buildNewOrder();
        Order secondOrder = testHelper.buildNewOrder();
        Order thirdOrder = testHelper.buildNewOrder();
        List<Order> expectedOrders = Lists.newArrayList(firstOrder, secondOrder, thirdOrder);

        expectedOrders.parallelStream().forEach(order -> jmsConsumer.receiveTickets(order));

        List<TicketOrder> actualTicketOrders = ticketOrderRepository.findAll();

        assertNotNull(actualTicketOrders);
        assertEquals(actualTicketOrders.size(), 3);
    }
}
