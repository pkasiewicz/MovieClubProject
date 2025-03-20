package pl.pkasiewicz.movieclub.domain.genre;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreRequestDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreResponseDto;
import pl.pkasiewicz.movieclub.domain.genre.exception.GenreNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GenreFacade {

    private final GenreRepository genreRepository;

    public GenreResponseDto saveGenre(GenreRequestDto dto) {
        Genre savedGenre = genreRepository.save(GenreMapper.mapToEntity(dto));
        return GenreMapper.mapToMovieResponseDto(savedGenre);
    }

    public GenreResponseDto removeGenreById(Long id) {
        Genre genre = genreRepository.deleteById(id);
        return GenreMapper.mapToMovieResponseDto(genre);
    }

    public GenreResponseDto findById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException("Genre with this id does not exist: " + id));
        return GenreMapper.mapToMovieResponseDto(genre);
    }

    public GenreResponseDto findByName(String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow(() -> new GenreNotFoundException("Genre with this name does not exist: " + name));
        return GenreMapper.mapToMovieResponseDto(genre);
    }

    public List<GenreResponseDto> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(GenreMapper::mapToMovieResponseDto)
                .collect(Collectors.toList());
    }

    public GenreResponseDto updateGenre(Long id, GenreRequestDto dto) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreNotFoundException("Genre with this id does not exist: " + id));
        Genre updatedGenre = Genre.builder()
                .id(genre.id())
                .name(dto.name())
                .description(dto.description())
                .build();
        return GenreMapper.mapToMovieResponseDto(genreRepository.save(updatedGenre));
    }
}
