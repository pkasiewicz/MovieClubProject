package pl.pkasiewicz.movieclub.domain.movie;

import lombok.Builder;
import pl.pkasiewicz.movieclub.domain.comment.Comment;
import pl.pkasiewicz.movieclub.domain.genre.Genre;
import pl.pkasiewicz.movieclub.domain.rating.Rating;

import java.util.Set;

@Builder
public record Movie(
        Long id,
        String title,
        String originalTitle,
        Integer releaseYear,
        String description,
        String shortDescription,
        String youtubeTrailerId,
        String poster,
        Set<Comment> comments,
        Genre genre,
        Set<Rating> ratings
        ) {
}
