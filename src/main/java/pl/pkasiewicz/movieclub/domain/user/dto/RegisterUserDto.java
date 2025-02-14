package pl.pkasiewicz.movieclub.domain.user.dto;

import lombok.Builder;

@Builder
public record RegisterUserDto(
        String username,
        String email,
        String password
) {
}
