package com.haidukov.producer;

import com.haidukov.pojo.Order;
import com.haidukov.pojo.Ticket;
import com.haidukov.producer.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.haidukov.producer.TestHelper.buildStaticTicket;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TicketControllerTest {

    @InjectMocks
    private TicketController ticketController;

    @Mock
    private TicketService ticketService;

    @Test
    public void addOrderTest() {
        Ticket ticket = buildStaticTicket();
        ticketController.addOrder(ticket);
        verify(ticketService).createOrder(ticket);
    }

    // BDD Test
    @Test
    public void bddSaveTicketTest() {
        // given
        Ticket ticket = buildStaticTicket();

        // when
        ticketController.addOrder(ticket);

        // then
        then(ticketService).should().createOrder(ticket);
    }
}
