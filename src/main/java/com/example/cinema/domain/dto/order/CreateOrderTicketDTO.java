package com.example.cinema.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateOrderTicketDTO {

    private int row;

    private int seat;

    private double price;

    private Long movieId;

}
