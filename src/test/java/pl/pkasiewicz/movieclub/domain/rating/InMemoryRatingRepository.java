package pl.pkasiewicz.movieclub.domain.rating;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRatingRepository implements RatingRepository {

    private final Map<Long, Rating> database = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Rating save(Rating rating) {
        Long id = idGenerator.getAndIncrement();
        Rating ratingToSave = Rating.builder()
                .id(id)
                .movie(rating.movie())
                .user(rating.user())
                .rating(rating.rating())
                .build();
        database.put(id, ratingToSave);
        return ratingToSave;
    }

    @Override
    public Optional<Rating> findByUser_UsernameAndMovie_Id(String username, Long movieId) {
        return database.values().stream()
                .filter(rating -> rating.user().username().equals(username))
                .filter(rating -> rating.movie().id().equals(movieId))
                .findFirst();
    }
}
