package com.haidukov.consumer;

import com.haidukov.consumer.service.TicketService;
import com.haidukov.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.haidukov.consumer.TestHelper.buildStaticOrder;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class JmsConsumerTest {

    @InjectMocks
    private JmsConsumer jmsConsumer;

    @Mock
    private TicketService ticketService;

    @Test
    public void testReceiveTickets() {
        Order expectedOrder = buildStaticOrder();
        jmsConsumer.receiveTickets(expectedOrder);
        verify(ticketService).saveTicket(expectedOrder);
    }

    // BDD Test
    @Test
    public void bddSaveTicketTest() {
        //given
        Order expectedOrder = buildStaticOrder();

        // when
        jmsConsumer.receiveTickets(expectedOrder);

        // then
        then(ticketService).should().saveTicket(expectedOrder);
    }
}
