package pl.pkasiewicz.movieclub.domain.genre;

import pl.pkasiewicz.movieclub.domain.genre.dto.GenreDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreRequestDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreResponseDto;

class GenreMapper {
    static Genre mapToEntity(GenreRequestDto dto) {
        return Genre.builder()
                .name(dto.name())
                .description(dto.description())
                .build();
    }

    static GenreResponseDto mapToMovieResponseDto(Genre entity) {
        return GenreResponseDto.builder()
                .id(entity.id())
                .name(entity.name())
                .description(entity.description())
                .build();
    }

    static Genre mapToEntityFromMovieDto(GenreDto genreDto) {
        return Genre.builder()
                .id(genreDto.id())
                .name(genreDto.name())
                .description(genreDto.description())
                .build();
    }
}
