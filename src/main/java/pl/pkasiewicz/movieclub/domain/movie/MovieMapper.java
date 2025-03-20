package pl.pkasiewicz.movieclub.domain.movie;

import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieRequestDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieResponseDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;

import java.util.ArrayList;
import java.util.HashSet;

public class MovieMapper {

    static Movie mapToEntity(MovieRequestDto dto) {
        return Movie.builder()
                .title(dto.title())
                .originalTitle(dto.originalTitle())
                .description(dto.description())
                .shortDescription(dto.shortDescription())
                .releaseYear(dto.releaseYear())
                .poster(dto.poster())
                .youtubeTrailerId(dto.youtubeTrailerId())
                .genres(dto.genres() != null ? new HashSet<>(dto.genres()) : new HashSet<>())
                .comments(new ArrayList<>())
                .ratings(new HashSet<>())
                .build();
    }

    static MovieResponseDto mapToMovieResponseDto(Movie movie) {
        return MovieResponseDto.builder()
                .id(movie.id())
                .title(movie.title())
                .originalTitle(movie.originalTitle())
                .description(movie.description())
                .shortDescription(movie.shortDescription())
                .releaseYear(movie.releaseYear())
                .genres(movie.genres())
                .poster(movie.poster())
                .youtubeTrailerId(movie.youtubeTrailerId())
                .build();
    }

    public static MovieDto mapFromMovieResponseDtoToMovieDto(MovieResponseDto movieResponseDto) {
        double avgRating = movieResponseDto.ratings().stream()
                .map(RatingDto::rating)
                .mapToDouble(val -> val)
                .average()
                .orElse(0);
        int ratingCount = movieResponseDto.ratings().size();
        return MovieDto.builder()
                .id(movieResponseDto.id())
                .title(movieResponseDto.title())
                .originalTitle(movieResponseDto.originalTitle())
                .description(movieResponseDto.description())
                .shortDescription(movieResponseDto.shortDescription())
                .releaseYear(movieResponseDto.releaseYear())
                .genres(movieResponseDto.genres())
                .poster(movieResponseDto.poster())
                .youtubeTrailerId(movieResponseDto.youtubeTrailerId())
                .avgRating(avgRating)
                .ratingCount(ratingCount)
                .comments(movieResponseDto.comments())
                .build();
    }
}
