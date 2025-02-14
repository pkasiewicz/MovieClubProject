package pl.pkasiewicz.movieclub.domain.user.dto;

import lombok.Builder;

@Builder
public record RegistrationResultDto(
        Long id,
        String username,
        String email,
        String password
) {
}
