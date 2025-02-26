package pl.pkasiewicz.movieclub.domain.genre;

import lombok.Builder;

@Builder
public record Genre (
        Long id,
        String name,
        String description
) {
}
