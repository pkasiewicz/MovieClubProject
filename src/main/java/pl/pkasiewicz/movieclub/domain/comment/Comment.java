package pl.pkasiewicz.movieclub.domain.comment;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

import java.time.LocalDateTime;

@Builder
record Comment(
        Long id,
        UserDto author,
        MovieDto movie,
        String message,
        LocalDateTime createDate
) {
}
