package pl.pkasiewicz.movieclub.domain.movie.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.genre.Genre;

@Builder
public record MovieSaveDto (
        String title,
        String originalTitle,
        Integer releaseYear,
        String description,
        String shortDescription,
        String youtubeTrailerId,
        String poster,
        Genre genre
) {

}
