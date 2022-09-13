package com.example.cinema.domain.dto.order;

import com.example.cinema.domain.dto.movie.MovieDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderTicketDTO {

    private Long id;

    private int row;

    private int seat;

    private double price;

    private MovieDTO movie;

}
