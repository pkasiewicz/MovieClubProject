package pl.pkasiewicz.movieclub.domain.user;

import lombok.Builder;

@Builder
public record UserRole (
        Long id,
        String name,
        String description
) {
}
