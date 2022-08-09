package com.haidukov.consumer.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "TICKET_ORDER")
public class TicketOrder {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @Column(name = "ticket_id")
    private String ticketId;

    @Column(name = "ticket_number")
    private int ticketNumber;

    @Column(name = "customer_name")
    private String customerName;

    public TicketOrder() {
        this.ticketId = null;
        this.ticketNumber = 0;
        this.customerName = null;
    }

    public TicketOrder(Long orderId, String ticketId, int ticketNumber, String customerName) {
        this.orderId = orderId;
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.customerName = customerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketOrder ticketOrder = (TicketOrder) o;
        return Objects.equals(orderId, ticketOrder.orderId) && Objects.equals(ticketId, ticketOrder.ticketId) && Objects.equals(ticketNumber, ticketOrder.ticketNumber) && Objects.equals(customerName, ticketOrder.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, ticketId, ticketNumber, customerName);
    }
}

