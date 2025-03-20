package pl.pkasiewicz.movieclub.domain.movie.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;

import java.util.List;
import java.util.Set;

@Builder
public record MovieResponseDto(
        Long id,
        String title,
        String originalTitle,
        String description,
        String shortDescription,
        Integer releaseYear,
        String poster,
        String youtubeTrailerId,
        Set<GenreDto> genres,
        List<CommentDto> comments,
        Set<RatingDto> ratings
) {
}
