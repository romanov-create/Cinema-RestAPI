package com.example.cinema.service;

import com.example.cinema.domain.dto.movie.CreateMovieDTO;
import com.example.cinema.domain.dto.movie.MovieDTO;
import com.example.cinema.domain.dto.movie.SearchMovieDTO;
import com.example.cinema.domain.dto.movie.UpdateMovieDTO;

import java.util.List;

public interface MovieService {

    MovieDTO create(CreateMovieDTO createMovieDTO);

    MovieDTO update(Long id, UpdateMovieDTO updateMovieDTO);

    MovieDTO getById(Long id);

    List<MovieDTO> searchBySpecification(SearchMovieDTO searchMovieDTO);

    void delete(Long id);

}
