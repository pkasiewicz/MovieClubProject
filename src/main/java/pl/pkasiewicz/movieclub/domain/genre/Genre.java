package pl.pkasiewicz.movieclub.domain.genre;

import lombok.Builder;

@Builder
record Genre(
        Long id,
        String name,
        String description
) {
}
