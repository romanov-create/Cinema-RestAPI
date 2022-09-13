package com.example.cinema.domain.dto.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateMovieDTO {

    private String title;

    private String description;

    private String genre;
}
