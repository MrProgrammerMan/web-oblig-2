package com.oslomet.mrprogrammerman.weboblig2.service;

import java.util.LinkedList;
import java.util.Queue;

public class TicketQueue {
    private Queue<String> queue;

    public TicketQueue() {
        queue = new LinkedList<>();
    }

    public void addTicket(String item) {
        queue.offer(item);
    }

    public void processTicket() {
        queue.poll();
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}