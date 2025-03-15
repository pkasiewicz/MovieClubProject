package pl.pkasiewicz.movieclub.domain.comment.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

@Builder
public record CommentRequestDto(
        UserDto author,
        MovieDto movie,
        String message
) {
}
