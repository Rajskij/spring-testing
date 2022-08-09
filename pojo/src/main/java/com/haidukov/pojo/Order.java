package com.haidukov.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Order {
    private final String orderId;

    private final Ticket ticket;

    private final Customer customer;

    @JsonCreator
    public Order(@JsonProperty("orderId") String orderId,
                 @JsonProperty("item") Ticket ticket,
                 @JsonProperty("customer") Customer customer) {
        this.orderId = orderId;
        this.ticket = ticket;
        this.customer = customer;
    }

    public String getOrderId() {
        return orderId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", item=" + ticket +
                ", customer=" + customer +
                '}';
    }
}