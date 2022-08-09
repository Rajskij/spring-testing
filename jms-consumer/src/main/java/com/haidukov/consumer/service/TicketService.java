package com.haidukov.consumer.service;

import com.haidukov.consumer.JmsConsumer;
import com.haidukov.consumer.entity.TicketOrder;
import com.haidukov.consumer.repository.TicketOrderRepository;
import com.haidukov.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final Logger LOGGER = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    private TicketOrderRepository ticketOrderRepository;

    public TicketOrder saveTicket(Order order) {
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(order.getTicket().getTicketId());
        ticketOrder.setTicketNumber(order.getTicket().getTicketNumber());
        ticketOrder.setCustomerName(order.getCustomer().getFullName());

        return ticketOrderRepository.save(ticketOrder);
    }
}
