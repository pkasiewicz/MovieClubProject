package pl.pkasiewicz.movieclub.domain.genre.dto;

import lombok.Builder;

@Builder
public record GenreResponseDto(
        Long id,
        String name,
        String description
) {
}
