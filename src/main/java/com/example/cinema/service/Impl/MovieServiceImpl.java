package com.example.cinema.service.Impl;

import com.example.cinema.domain.data.Movie;
import com.example.cinema.domain.dto.movie.CreateMovieDTO;
import com.example.cinema.domain.dto.movie.MovieDTO;
import com.example.cinema.domain.dto.movie.SearchMovieDTO;
import com.example.cinema.domain.dto.movie.UpdateMovieDTO;
import com.example.cinema.domain.mapper.MovieMapper;
import com.example.cinema.repository.MovieRepository;
import com.example.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.cinema.service.specifications.MovieSpecification.hasTitle;
import static com.example.cinema.service.specifications.MovieSpecification.hasDescription;
import static com.example.cinema.service.specifications.MovieSpecification.hasGenre;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieMapper movieMapper;

    @Override
    public MovieDTO create(CreateMovieDTO createMovieDTO) {
        Movie movie = movieMapper.toCreateEntity(createMovieDTO);
        return movieMapper.toDto(movieRepository.save(movie));
    }

    @Override
    public MovieDTO update(Long id, UpdateMovieDTO updateMovieDTO) {
        Movie movie = movieRepository.getReferenceById(id);
        movie = movieMapper.toUpdateEntity(movie, updateMovieDTO);

        return movieMapper.toDto(movieRepository.save(movie));
    }

    @Override
    public MovieDTO getById(Long id) {
        return movieMapper.toDto(movieRepository.getReferenceById(id));
    }

    @Override
    public List<MovieDTO> searchBySpecification(SearchMovieDTO searchMovieDTO) {
        List<Movie> movieList = movieRepository.findAll(Specification.where(hasTitle(searchMovieDTO.getTitle()))
                .and(hasDescription(searchMovieDTO.getDescription()))
                .and(hasGenre(searchMovieDTO.getGenre())));

        return movieMapper.toDto(movieList);
    }

    @Override
    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

}
