package pl.pkasiewicz.movieclub.domain.rating;

import java.util.List;
import java.util.Optional;

public interface RatingRepository {

    Optional<Rating> findByUser_UsernameAndMovie_Id(String username, Long movieId);
    Optional<List<Rating>> findByMovie_Id(Long movieId);
    Rating save(Rating rating);
}
