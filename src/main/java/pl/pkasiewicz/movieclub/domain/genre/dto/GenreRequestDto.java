package pl.pkasiewicz.movieclub.domain.genre.dto;

import lombok.Builder;

@Builder
public record GenreRequestDto(
        String name,
        String description
) {
}
