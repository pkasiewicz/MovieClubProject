package pl.pkasiewicz.movieclub.domain.movie;

import java.util.List;
import java.util.Optional;

interface MovieRepository {
    Optional<Movie> findByTitle(String title);
    Optional<Movie> findById(Long id);
    List<Movie> findAll();
    Movie save(Movie movie);
    Movie deleteById(Long id);
}
