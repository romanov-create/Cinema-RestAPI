package com.example.cinema.domain.mapper;

import com.example.cinema.domain.data.Movie;
import com.example.cinema.domain.dto.movie.CreateMovieDTO;
import com.example.cinema.domain.dto.movie.MovieDTO;
import com.example.cinema.domain.dto.movie.UpdateMovieDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    public Movie toCreateEntity(CreateMovieDTO createMovieDTO) {
        Movie movie = new Movie();

        movie.setTitle(createMovieDTO.getTitle());
        movie.setDescription(createMovieDTO.getDescription());
        movie.setGenre(createMovieDTO.getGenre());

        return movie;
    }

    public Movie toUpdateEntity(Movie movie, UpdateMovieDTO updateMovieDTO) {
        movie.setTitle(updateMovieDTO.getTitle());
        movie.setDescription(updateMovieDTO.getDescription());
        movie.setGenre(updateMovieDTO.getGenre());

        return movie;
    }

    public MovieDTO toDto(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();

        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setDescription(movie.getDescription());
        movieDTO.setGenre(movie.getGenre());

        return movieDTO;
    }

    public List<MovieDTO> toDto(List<Movie> movieList) {
        List<MovieDTO> movieDTOList = new ArrayList<>();

        movieList.forEach(movie -> {
            MovieDTO movieDTO = new MovieDTO();

            movieDTO.setId(movie.getId());
            movieDTO.setTitle(movie.getTitle());
            movieDTO.setDescription(movie.getDescription());
            movieDTO.setGenre(movie.getGenre());

            movieDTOList.add(movieDTO);
        });

        return movieDTOList;
    }

}
