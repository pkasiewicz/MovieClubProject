package pl.pkasiewicz.movieclub.domain.movie.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreDto;

import java.util.Set;

@Builder
public record MovieRequestDto(
        String title,
        String originalTitle,
        Integer releaseYear,
        String description,
        String shortDescription,
        String youtubeTrailerId,
        String poster,
        Set<GenreDto> genres
) {
}
