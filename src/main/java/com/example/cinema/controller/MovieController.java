package com.example.cinema.controller;

import com.example.cinema.domain.dto.movie.CreateMovieDTO;
import com.example.cinema.domain.dto.movie.MovieDTO;
import com.example.cinema.domain.dto.movie.SearchMovieDTO;
import com.example.cinema.domain.dto.movie.UpdateMovieDTO;
import com.example.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public MovieDTO create(@RequestBody CreateMovieDTO createMovieDTO) {
        return movieService.create(createMovieDTO);
    }

    @GetMapping("/{id}")
    public MovieDTO getById(@PathVariable Long id) {
        return movieService.getById(id);
    }

    @GetMapping("/search-by-specification")
    public List<MovieDTO> searchBySpecification(@RequestBody SearchMovieDTO searchMovieDTO) {
        return movieService.searchBySpecification(searchMovieDTO);
    }

    @PutMapping("/{id}")
    public MovieDTO update(@PathVariable Long id, @RequestBody UpdateMovieDTO updateMovieDTO) {
        return movieService.update(id, updateMovieDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        movieService.delete(id);
    }

}
