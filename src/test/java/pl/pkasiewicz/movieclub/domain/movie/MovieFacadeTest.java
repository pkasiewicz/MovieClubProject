package pl.pkasiewicz.movieclub.domain.movie;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieRequestDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieResponseDto;
import pl.pkasiewicz.movieclub.domain.movie.exceptions.MovieAlreadyExistsException;
import pl.pkasiewicz.movieclub.domain.movie.exceptions.MovieNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertAll;

class MovieFacadeTest {

    private final MovieFacade movieFacade = new MovieFacade(
            new InMemoryMovieRepository()
    );

    @Test
    void should_add_movie() {
        // given
        MovieRequestDto expected = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);
        // when
        MovieResponseDto actual = movieFacade.addMovie(expected);
        // then
        assertAll(
                () -> assertThat(actual.title()).isEqualTo(expected.title()),
                () -> assertThat(actual.originalTitle()).isEqualTo(expected.originalTitle()),
                () -> assertThat(actual.description()).isEqualTo(expected.description()),
                () -> assertThat(actual.shortDescription()).isEqualTo(expected.shortDescription()),
                () -> assertThat(actual.releaseYear()).isEqualTo(expected.releaseYear()),
                () -> assertThat(actual.poster()).isEqualTo(expected.poster()),
                () -> assertThat(actual.youtubeTrailerId()).isEqualTo(expected.youtubeTrailerId())
        );
    }

    @Test
    void should_return_movie_by_title() {
        // given
        MovieRequestDto movieToSave = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);
        MovieResponseDto expected = movieFacade.addMovie(movieToSave);
        // when
        MovieResponseDto actual = movieFacade.findMovieByTitle("test");
        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_movie_by_id() {
        MovieRequestDto movieToSave = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);
        MovieResponseDto expected = movieFacade.addMovie(movieToSave);
        // when
        MovieResponseDto actual = movieFacade.findMovieById(expected.id());
        // then
        assertThat(actual).isNotNull();
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_return_all_movies() {
        // given
        List<MovieRequestDto> movieRequestDtos = List.of(
                new MovieRequestDto("test1", "test1", 1234, "test1", "test1", "test1", "test1", null),
                new MovieRequestDto("test2", "test2", 1234, "test2", "test2", "test2", "test2", null),
                new MovieRequestDto("test3", "test3", 1234, "test3", "test3", "test3", "test3", null)
        );
        movieRequestDtos.forEach(movieFacade::addMovie);
        // when
        List<MovieResponseDto> actual = movieFacade.findAllMovies();
        // then
        assertThat(actual).hasSize(3);
    }

    @Test
    void should_return_empty_list_of_movies() {
        // given && when
        List<MovieResponseDto> actual = movieFacade.findAllMovies();
        // then
        assertThat(actual).isEmpty();
    }

    @Test
    void should_throw_exception_if_movie_not_found_by_id() {
        // given
        long id = 1L;
        // when
        Throwable thrown = catchThrowable(() -> movieFacade.findMovieById(id));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining(String.format("Movie with this id does not exist: %s", id));
    }

    @Test
    void should_throw_exception_if_movie_not_found_by_title() {
        // given
        String title = "test";
        // when
        Throwable thrown = catchThrowable(() -> movieFacade.findMovieByTitle(title));
        //then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(MovieNotFoundException.class)
                .hasMessageContaining(String.format("Movie with this title does not exist: %s", title));
    }

    @Test
    void should_throw_exception_when_movie_title_duplicate() {
        // given
        MovieRequestDto movieToSave = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);
        movieFacade.addMovie(movieToSave);
        // when
        Throwable thrown = catchThrowable(() -> movieFacade.addMovie(movieToSave));
        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(MovieAlreadyExistsException.class)
                .hasMessageContaining("Movie with this title already exists: %s", movieToSave.title());
    }

    @Test
    void should_remove_movie_by_id() {
        // given
        MovieRequestDto movie = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);
        MovieResponseDto actual = movieFacade.addMovie(movie);
        // when
        MovieResponseDto expected = movieFacade.deleteMovieById(actual.id());
        // then
        assertThat(movieFacade.findAllMovies()).isEmpty();
        assertThat(actual).isEqualTo(expected);
    }
}