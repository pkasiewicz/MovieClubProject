package pl.pkasiewicz.movieclub.domain.genre;

import java.util.List;
import java.util.Optional;

interface GenreRepository {
    Optional<Genre> findByName(String name);
    Optional<Genre> findById(Long id);
    List<Genre> findAll();
    Genre save(Genre genre);
    Genre deleteById(Long id);
}
