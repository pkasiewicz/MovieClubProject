package pl.pkasiewicz.movieclub.domain.rating;

import java.util.Optional;

interface RatingRepository {

    Optional<Rating> findByUser_UsernameAndMovie_Id(String username, Long movieId);
    Rating save(Rating rating);
}
