package pl.pkasiewicz.movieclub.domain.genre;

import pl.pkasiewicz.movieclub.domain.genre.exception.GenreAlreadyExistsException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public class InMemoryGenreRepository implements GenreRepository {

    Map<Long, Genre> database = new ConcurrentHashMap<>();

    @Override
    public Optional<Genre> findByName(String name) {
        return database.values()
                .stream()
                .filter(genre -> genre.name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return database.values()
                .stream()
                .filter(genre -> genre.id().equals(id))
                .findFirst();
    }

    @Override
    public List<Genre> findAll() {
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public Genre save(Genre genre) {
        if (database.values().stream().anyMatch(entity -> entity.name().equals(genre.name()))) {
            throw new GenreAlreadyExistsException("Genre with this name already exists: " + genre.name());
        }
        Long id = ThreadLocalRandom.current().nextLong();
        Genre genreToSave = Genre.builder()
                .id(id)
                .name(genre.name())
                .description(genre.description())
                .build();
        database.put(id, genreToSave);
        return genreToSave;
    }

    @Override
    public Genre deleteById(Long id) {
        return database.remove(id);
    }
}
