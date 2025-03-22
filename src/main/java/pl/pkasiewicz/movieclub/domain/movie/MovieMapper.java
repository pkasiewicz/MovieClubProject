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
        double avgRating = movie.ratings() == null ? 0 :
                movie.ratings().stream()
                        .map(RatingDto::rating)
                        .mapToDouble(val -> val)
                        .average()
                        .orElse(0);
        int ratingCount = movie.ratings() == null ? 0 : movie.ratings().size();
        return MovieResponseDto.builder()
                .id(movie.id())
                .title(movie.title())
                .originalTitle(movie.originalTitle())
                .description(movie.description())
                .shortDescription(movie.shortDescription())
                .releaseYear(movie.releaseYear())
                .genres(movie.genres() != null ? new HashSet<>(movie.genres()) : new HashSet<>()    )
                .poster(movie.poster())
                .youtubeTrailerId(movie.youtubeTrailerId())
                .avgRating(avgRating)
                .ratingCount(ratingCount)
                .comments(movie.comments() != null ? new ArrayList<>(movie.comments()) : new ArrayList<>())
                .build();
    }

    public static MovieDto mapFromMovieResponseDtoToMovieDto(MovieResponseDto movieResponseDto) {
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
                .avgRating(movieResponseDto.avgRating())
                .ratingCount(movieResponseDto.ratingCount())
                .comments(movieResponseDto.comments())
                .build();
    }
}
