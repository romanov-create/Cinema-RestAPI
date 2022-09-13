package com.example.cinema.domain.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "row_place")
    private int rowPlace;

    @Column(name = "seat")
    private int seat;

    @Column(name = "price")
    private double price;

    @ManyToOne
    private Movie movie;

    public OrderTicket(Long id, int rowPlace, int seat, double price) {
        this.id = id;
        this.rowPlace = rowPlace;
        this.seat = seat;
        this.price = price;
    }
}
