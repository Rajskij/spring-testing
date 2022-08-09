package com.haidukov.consumer.service;

import com.haidukov.consumer.entity.TicketOrder;
import com.haidukov.consumer.repository.TicketOrderRepository;
import com.haidukov.pojo.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.haidukov.consumer.TestHelper.buildStaticOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class TicketServiceIT {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Test
    public void testSaveTicket() {
        Order expectedOrder = buildStaticOrder();

        ticketService.saveTicket(expectedOrder);

        TicketOrder actualTicketOrder = ticketOrderRepository.findAll().stream()
                .filter(ticketOrder -> ticketOrder.getTicketId().equals(expectedOrder.getTicket().getTicketId()))
                .findFirst()
                .orElse(null);

        assertNotNull(actualTicketOrder);
        assertEquals(actualTicketOrder.getTicketId(), expectedOrder.getTicket().getTicketId());
        assertEquals(actualTicketOrder.getTicketNumber(), expectedOrder.getTicket().getTicketNumber());
        assertEquals(actualTicketOrder.getCustomerName(), expectedOrder.getCustomer().getFullName());
    }
}
