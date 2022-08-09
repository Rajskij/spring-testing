package com.haidukov.consumer.service;

import com.haidukov.consumer.entity.TicketOrder;
import com.haidukov.consumer.repository.TicketOrderRepository;
import com.haidukov.pojo.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.haidukov.consumer.TestHelper.buildStaticOrder;
import static com.haidukov.consumer.TestHelper.buildStaticTicketOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicketServiceTest {

    @InjectMocks
    private TicketService ticketService;

    @Mock
    private TicketOrderRepository ticketOrderRepository;

    @Test
    public void testSaveTicket() {
        Order expectedOrder = buildStaticOrder();
        TicketOrder expectedTicketOrder = buildStaticTicketOrder(expectedOrder);

        when(ticketOrderRepository.save(any(TicketOrder.class))).thenReturn(expectedTicketOrder);

        TicketOrder actualTicketOrder = ticketService.saveTicket(expectedOrder);

        assertEquals(actualTicketOrder.getTicketId(), expectedOrder.getTicket().getTicketId());
        assertEquals(actualTicketOrder.getTicketNumber(), expectedOrder.getTicket().getTicketNumber());
        assertEquals(actualTicketOrder.getCustomerName(), expectedOrder.getCustomer().getFullName());
    }

    // BDD Test
    @Test
    public void bddSaveTicketTest() {
        // given
        Order expectedOrder = buildStaticOrder();
        TicketOrder expectedTicketOrder = buildStaticTicketOrder(expectedOrder);
        given(ticketOrderRepository.save(any(TicketOrder.class)))
                .willReturn(expectedTicketOrder);

        // when
        ticketService.saveTicket(expectedOrder);

        // then
        then(ticketOrderRepository).should().save(expectedTicketOrder);
    }
}
