package pl.pkasiewicz.movieclub.domain.user.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;
import pl.pkasiewicz.movieclub.domain.user.UserRole;

import java.util.List;
import java.util.Set;

@Builder
public record UserDto(
        Long id,
        String username,
        String email,
        String password,
        Set<UserRole> roles,
        List<CommentDto> comments,
        Set<RatingDto> ratings
) {
}
