package pl.pkasiewicz.movieclub.domain.rating;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.Movie;
import pl.pkasiewicz.movieclub.domain.user.User;

@Builder
public record Rating (
        Long id,
        Movie movie,
        User user,
        Integer rating
) {
}
