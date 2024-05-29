package com.example.trainticketing.service;

import com.example.trainticketing.model.Ticket;
import com.example.trainticketing.model.User;
import com.example.trainticketing.repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceTest {

    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        ticketRepository = mock(TicketRepository.class);
        ticketService = new TicketService(ticketRepository);
    }

    @Test
    void purchaseTicket() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0, "A1");
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket purchasedTicket = ticketService.purchaseTicket("London", "France", user);

        assertEquals("John", purchasedTicket.getUser().getFirstName());
        assertEquals("Doe", purchasedTicket.getUser().getLastName());
        assertEquals("john.doe@example.com", purchasedTicket.getUser().getEmail());
        assertEquals("A1", purchasedTicket.getSeat());
    }

    @Test
    void getReceipt() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0, "A1");
        when(ticketRepository.findById("john.doe@example.com")).thenReturn(Optional.of(ticket));

        Optional<Ticket> receipt = ticketService.getReceipt("john.doe@example.com");

        assertTrue(receipt.isPresent());
        assertEquals("John", receipt.get().getUser().getFirstName());
    }

    @Test
    void removeUser() {
        User user = new User("John", "Doe", "john.doe@example.com");
        Ticket ticket = new Ticket("London", "France", user, 20.0, "A1");
        when(ticketRepository.findById("john.doe@example.com")).thenReturn(Optional.of(ticket));

        ticketService.removeUser("john.doe@example.com");

        verify(ticketRepository, times(1)).delete(ticket);
    }

    @Test
    void modifyUserSeat() {
        User user = new User("John", "Doe", "john.doe@example.com", null);
        Ticket ticket = new Ticket("London", "France", user, 20.0, "A1");
        when(ticketRepository.findById("john.doe@example.com")).thenReturn(Optional.of(ticket));

        ticketService.modifyUserSeat("john.doe@example.com", "B1");

        verify(ticketRepository, times(1)).updateSeat(ticket, "B1");
    }
}
