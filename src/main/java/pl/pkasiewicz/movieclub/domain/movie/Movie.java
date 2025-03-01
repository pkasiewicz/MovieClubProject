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
        String description,
        String shortDescription,
        Integer releaseYear,
        String poster,
        String youtubeTrailerId,
        Genre genre,
        Set<Comment> comments,
        Set<Rating> ratings
        ) {
}
