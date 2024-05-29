package com.example.trainticketing.service;

import com.example.trainticketing.model.Ticket;
import com.example.trainticketing.model.User;
import com.example.trainticketing.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final Random random = new Random();

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket purchaseTicket(String from, String to, User user) {
        String seat = allocateSeat();
        String ticketNumber = generateTicketNumber(); 
        Ticket ticket = new Ticket(ticketNumber, user.getFirstName() + " " + user.getLastName(), "Train123", "2024-06-01T10:00:00", seat);
        ticket.setUserEmail(user.getEmail()); // Set userEmail here
        ticket.setUserPaid(user.getPaid());
        return ticketRepository.save(ticket);
    }


    public Optional<Ticket> getReceipt(String user_email) {
        return ticketRepository.findByUserEmail(user_email);
    }

    public List<Ticket> getUsersBySection(String section) {
        return ticketRepository.findAll().stream()
                .filter(ticket -> ticket.getSeat().startsWith(section))
                .collect(Collectors.toList());
    }

    public void removeUser(String email) {
        ticketRepository.findByUserEmail(email).ifPresent(ticketRepository::delete);
    }

    public void modifyUserSeat(String email, String newSeat) {
        ticketRepository.findByUserEmail(email).ifPresent(ticket -> {
            ticket.setSeat(newSeat);
            ticketRepository.save(ticket);
        });
    }

    private String allocateSeat() {
        String section = random.nextBoolean() ? "A" : "B";
        int seatNumber = random.nextInt(50) + 1;
        return section + seatNumber;
    }
    
    private String generateTicketNumber() {
        return "TICKET-" + random.nextInt(100000);
    }
}
