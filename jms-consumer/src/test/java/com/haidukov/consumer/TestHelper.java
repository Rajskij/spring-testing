package com.haidukov.consumer;

import com.haidukov.consumer.entity.TicketOrder;
import com.haidukov.pojo.Customer;
import com.haidukov.pojo.Order;
import com.haidukov.pojo.Ticket;

import java.util.UUID;

public class TestHelper {

    public Order buildNewOrder() {
        Ticket expectedTicket = new Ticket(generateUuid(), 3);
        Customer expectedCustomer = new Customer(generateUuid(), "Default Customer");
        return new Order(generateUuid(), expectedTicket, expectedCustomer);
    }

    public static Order buildStaticOrder() {
        Ticket expectedTicket = new Ticket(generateUuid(), 3);
        Customer expectedCustomer = new Customer(generateUuid(), "Default Customer");
        return new Order(generateUuid(), expectedTicket, expectedCustomer);
    }

    public static TicketOrder buildStaticTicketOrder(Order order) {
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setTicketId(order.getTicket().getTicketId());
        ticketOrder.setTicketNumber(order.getTicket().getTicketNumber());
        ticketOrder.setCustomerName(order.getCustomer().getFullName());
        return ticketOrder;
    }

    private static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
