package pl.pkasiewicz.movieclub.domain.user;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.Comment;

import java.util.Set;

@Builder
public record User(
        Long id,
        String username,
        String email,
        String password,
        Set<UserRole> roles,
        Set<Comment> comments
) {
}
