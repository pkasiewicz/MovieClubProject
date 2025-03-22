package pl.pkasiewicz.movieclub.domain.comment.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

import java.time.LocalDateTime;

@Builder
public record CommentResponseDto(
        Long id,
        String message,
        UserDto author,
        MovieDto movie,
        LocalDateTime createDate
) {
}
