package com.example.trainticketing.controller;

import com.example.trainticketing.model.*;
import com.example.trainticketing.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketRequest ticketRequest) {
        Ticket ticket = ticketService.purchaseTicket(ticketRequest.getFrom(), ticketRequest.getTo(), ticketRequest.getUser());
        return ResponseEntity.ok(ticket);
    }

    @PostMapping("/receipt")
    public ResponseEntity<Ticket> getReceipt(@RequestBody UserEmailRequest request) {
        Optional<Ticket> ticket = ticketService.getReceipt(request.getEmail());
        return ticket.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/users")
    public ResponseEntity<List<Ticket>> getUsersBySection(@RequestBody SectionRequest sectionRequest) {
        List<Ticket> tickets = ticketService.getUsersBySection(sectionRequest.getSection());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeUser(@RequestBody UserEmailRequest request) {
        ticketService.removeUser(request.getEmail());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify-seat")
    public ResponseEntity<Void> modifyUserSeat(@RequestBody ModifySeatRequest request) {
        ticketService.modifyUserSeat(request.getEmail(), request.getNewSeat());
        return ResponseEntity.noContent().build();
    }
}
