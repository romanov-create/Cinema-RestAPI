package com.example.cinema.domain.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchOrderTicketDTO {

    private Integer row;

    private Integer seat;

    private Double minPrice;

    private Double maxPrice;

    private Long movieId;

}
