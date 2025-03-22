package pl.pkasiewicz.movieclub.domain.rating;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

@Builder
record Rating(
        Long id,
        MovieDto movie,
        UserDto user,
        Integer rating
) {
}
