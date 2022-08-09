package com.haidukov.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {
    private String ticketId;

    private int ticketNumber;

    @JsonCreator
    public Ticket(@JsonProperty("itemId") String ticketId, @JsonProperty("itemNumber") int ticketNumber) {
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
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
    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + ticketId + '\'' +
                ", itemNumber=" + ticketNumber +
                '}';
    }
}
