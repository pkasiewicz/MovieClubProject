package pl.pkasiewicz.movieclub.domain.comment;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.Movie;
import pl.pkasiewicz.movieclub.domain.user.User;

import java.time.LocalDateTime;

@Builder
public record Comment (
        Long id,
        User author,
        Movie movie,
        String message,
        LocalDateTime createDate
) {
}
