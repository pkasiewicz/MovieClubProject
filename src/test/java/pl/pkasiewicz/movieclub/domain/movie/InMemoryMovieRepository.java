package pl.pkasiewicz.movieclub.domain.movie;

import pl.pkasiewicz.movieclub.domain.movie.exceptions.MovieAlreadyExistsException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

class InMemoryMovieRepository implements MovieRepository {

    Map<Long, Movie> database = new ConcurrentHashMap<>();

    @Override
    public Optional<Movie> findByTitle(String title) {
        return database.values()
                .stream()
                .filter(movie -> movie.title().equals(title))
                .findFirst();
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return database.values()
                .stream()
                .toList();
    }

    @Override
    public Movie save(Movie movie) {
        if (database.values().stream().anyMatch(entity -> entity.title().equals(movie.title())))
            throw new MovieAlreadyExistsException("Movie with this title already exists: " + movie.title());
        Long id = ThreadLocalRandom.current().nextLong();
        Movie movieToSave = Movie.builder()
                .id(id)
                .title(movie.title())
                .originalTitle(movie.originalTitle())
                .description(movie.description())
                .shortDescription(movie.shortDescription())
                .releaseYear(movie.releaseYear())
                .poster(movie.poster())
                .youtubeTrailerId(movie.youtubeTrailerId())
                .genre(movie.genre())
                .build();
        database.put(id, movieToSave);
        return movieToSave;
    }
}
