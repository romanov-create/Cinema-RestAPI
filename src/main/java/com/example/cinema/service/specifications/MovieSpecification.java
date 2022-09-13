package com.example.cinema.service.specifications;

import com.example.cinema.domain.data.Movie;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecification {

    public static Specification<Movie> hasTitle(String title) {
        return (root, query, cb) -> cb.equal(root.<String>get("title"), title);
    }

    public static Specification<Movie> hasDescription(String description) {
        return (root, query, cb) -> cb.equal(root.<String>get("description"), description);
    }

    public static Specification<Movie> hasGenre(String genre) {
        return (root, query, cb) -> cb.equal(root.<String>get("genre"), genre);
    }

}
