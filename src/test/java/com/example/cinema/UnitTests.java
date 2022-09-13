package com.example.cinema;

import com.example.cinema.domain.data.Movie;
import com.example.cinema.domain.data.OrderTicket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;


public class UnitTests {

    @Test
    public void createMovieEntityTest() {
        Movie movie = new Movie(1L, "test", "test description", "action");

        Assertions.assertEquals(1, movie.getId());
        Assertions.assertEquals("test", movie.getTitle());
        Assertions.assertEquals("test description", movie.getDescription());
        Assertions.assertEquals("action", movie.getGenre());
    }

    @Test
    public void createOrderTicketEntityTest() {
        Movie movie = new Movie(1L, "test", "test description", "action");
        OrderTicket orderTicket = new OrderTicket(1L, 6, 3, 114.50, movie);

        Assertions.assertEquals(1, orderTicket.getId());
        Assertions.assertEquals(6, orderTicket.getRowPlace());
        Assertions.assertEquals(3, orderTicket.getSeat());
        Assertions.assertEquals(114.50, orderTicket.getPrice());
    }

    @Test
    public void setMovieEntityToOrderTicketTest() {
        Movie movie = new Movie(1L, "test", "test description", "action");
        OrderTicket orderTicket = new OrderTicket(1L, 6, 3, 114.50, movie);

        Assertions.assertEquals(movie.getId(), orderTicket.getMovie().getId());
    }

    @Test
    public void setOrderTicketEntityToMovieTest() {
        OrderTicket orderTicket = new OrderTicket(1L, 6, 3, 114.50);
        Movie movie = new Movie(1L, "test", "test description", "action", Collections.singletonList(orderTicket));

        Assertions.assertEquals(movie.getOrders().stream().findFirst().get().getId(), orderTicket.getId());
    }

}
