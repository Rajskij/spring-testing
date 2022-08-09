package com.haidukov.producer;

import com.haidukov.pojo.Order;
import com.haidukov.pojo.Ticket;
import com.haidukov.producer.TicketController;
import com.haidukov.producer.service.TicketService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static com.haidukov.producer.TestHelper.buildStaticTicket;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class TicketControllerIT {

    @Autowired
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Test
    public void addOrderTest() {
        Ticket ticket = buildStaticTicket();
        ticketController.addOrder(ticket);
        verify(ticketService).createOrder(ticket);
    }
}
