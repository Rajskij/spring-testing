package com.haidukov.producer;

import com.haidukov.pojo.Ticket;
import com.haidukov.producer.service.TicketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public void addOrder(@RequestBody Ticket ticket) {
        ticketService.createOrder(ticket);
    }
}
