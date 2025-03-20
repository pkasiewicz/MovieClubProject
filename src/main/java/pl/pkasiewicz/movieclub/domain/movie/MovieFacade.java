package pl.pkasiewicz.movieclub.domain.movie;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieRequestDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieResponseDto;
import pl.pkasiewicz.movieclub.domain.movie.exceptions.MovieNotFoundException;

import java.util.List;

@AllArgsConstructor
public class MovieFacade {

    private final MovieRepository movieRepository;

    public MovieResponseDto addMovie(MovieRequestDto movieRequestDto) {
        Movie savedMovie = movieRepository.save(MovieMapper.mapToEntity(movieRequestDto));
        return MovieMapper.mapToMovieResponseDto(savedMovie);
    }

    public MovieResponseDto findMovieById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie with this id does not exist: " + id));
        return MovieMapper.mapToMovieResponseDto(movie);
    }

    public List<MovieResponseDto> findAllMovies() {
        return movieRepository.findAll().stream().map(MovieMapper::mapToMovieResponseDto).toList();
    }

    public MovieResponseDto findMovieByTitle(String title) {
        Movie movie = movieRepository.findByTitle(title)
                .orElseThrow(() -> new MovieNotFoundException("Movie with this title does not exist: " + title));
        return MovieMapper.mapToMovieResponseDto(movie);
    }

    public MovieResponseDto deleteMovieById(Long id) {
        Movie movie = movieRepository.deleteById(id);
        return MovieMapper.mapToMovieResponseDto(movie);
    }
}
