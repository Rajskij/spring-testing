package com.haidukov.consumer.repository;

import com.haidukov.consumer.config.DbConfigure;
import com.haidukov.consumer.entity.TicketOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = {DbConfigure.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class TicketOrderRepositoryIT {

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    @Test
    public void givenTicket_whenSave_ThanGetOk() {
        TicketOrder expectedTicketOrder = new TicketOrder(1L, "12", 3, "Default Customer");

        ticketOrderRepository.save(expectedTicketOrder);

        List<TicketOrder> ticketOrders = ticketOrderRepository.findAll();
        TicketOrder actualTicketOrder = ticketOrders.get(0);
        assertEquals(actualTicketOrder.getOrderId(), expectedTicketOrder.getOrderId());
        assertEquals(actualTicketOrder.getTicketId(), expectedTicketOrder.getTicketId());
        assertEquals(actualTicketOrder.getTicketNumber(), expectedTicketOrder.getTicketNumber());
        assertEquals(actualTicketOrder.getCustomerName(), expectedTicketOrder.getCustomerName());
    }
}
