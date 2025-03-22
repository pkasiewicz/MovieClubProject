package pl.pkasiewicz.movieclub.domain.movie.dto;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreDto;

import java.util.List;
import java.util.Set;

@Builder
public record MovieResponseDto(
        Long id,
        String title,
        String originalTitle,
        Integer releaseYear,
        String description,
        String shortDescription,
        String youtubeTrailerId,
        String poster,
        List<CommentDto> comments,
        Set<GenreDto> genres,
        double avgRating,
        int ratingCount
) {
}


