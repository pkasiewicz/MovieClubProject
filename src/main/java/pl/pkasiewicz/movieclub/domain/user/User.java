package pl.pkasiewicz.movieclub.domain.user;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;

import java.util.List;
import java.util.Set;

@Builder
record User(
        Long id,
        String username,
        String email,
        String password,
        Set<UserRole> roles,
        List<CommentDto> comments,
        Set<RatingDto> ratings
) {
}
