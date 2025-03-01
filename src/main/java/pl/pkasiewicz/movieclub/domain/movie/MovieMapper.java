package pl.pkasiewicz.movieclub.domain.movie;

import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieSaveDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieResponseDto;

class MovieMapper {

    static Movie mapToEntity(MovieSaveDto dto) {
        return Movie.builder()
                .title(dto.title())
                .originalTitle(dto.originalTitle())
                .description(dto.description())
                .shortDescription(dto.shortDescription())
                .releaseYear(dto.releaseYear())
                .genre(dto.genre())
                .poster(dto.poster())
                .youtubeTrailerId(dto.youtubeTrailerId())
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
                .genre(movie.genre())
                .poster(movie.poster())
                .youtubeTrailerId(movie.youtubeTrailerId())
                .build();
    }

    static Movie mapToEntityFromMovieDto(MovieDto movieDto) {
        return Movie.builder()
                .id(movieDto.id())
                .title(movieDto.title())
                .originalTitle(movieDto.originalTitle())
                .description(movieDto.description())
                .shortDescription(movieDto.shortDescription())
                .releaseYear(movieDto.releaseYear())
                .genre(movieDto.genre())
                .poster(movieDto.poster())
                .youtubeTrailerId(movieDto.youtubeTrailerId())
                .ratings(movieDto.ratings())
                .comments(movieDto.comments())
                .build();
    }
}
