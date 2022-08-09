package com.haidukov.producer;

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

    public static Ticket buildStaticTicket() {
        return new Ticket(generateUuid(), 3);
    }

    private static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
