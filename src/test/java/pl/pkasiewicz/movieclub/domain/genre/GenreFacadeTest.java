package pl.pkasiewicz.movieclub.domain.genre;

import org.junit.jupiter.api.Test;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreRequestDto;
import pl.pkasiewicz.movieclub.domain.genre.dto.GenreResponseDto;
import pl.pkasiewicz.movieclub.domain.genre.exception.GenreAlreadyExistsException;
import pl.pkasiewicz.movieclub.domain.genre.exception.GenreNotFoundException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class GenreFacadeTest {

    private final GenreFacade genreFacade = new GenreFacade(
            new InMemoryGenreRepository()
    );

    @Test
    void should_find_genre_by_id() {
        // given
        GenreResponseDto expected = genreFacade.saveGenre(new GenreRequestDto("test", "test"));
        // when
        GenreResponseDto actual = genreFacade.findById(expected.id());
        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void should_find_genre_by_name() {
        // given
        GenreRequestDto expected = new GenreRequestDto("test", "test");
        genreFacade.saveGenre(expected);
        // when
        GenreResponseDto actual = genreFacade.findByName(expected.name());
        // then
        assertThat(actual.name()).isEqualTo(expected.name());
        assertThat(actual.description()).isEqualTo(expected.description());
    }

    @Test
    void should_find_all_genres() {
        // given
        List<GenreRequestDto> genresToSave = List.of(
                new GenreRequestDto("test1", "test1"),
                new GenreRequestDto("test2", "test2"),
                new GenreRequestDto("test3", "test3")
        );
        genresToSave.forEach(genreFacade::saveGenre);
        // when
        List<GenreResponseDto> all = genreFacade.findAll();
        // then
        assertThat(all).hasSize(3);
    }

    @Test
    void should_add_genre() {
        // given
        GenreRequestDto expected = new GenreRequestDto("test", "test");
        // when
        GenreResponseDto actual = genreFacade.saveGenre(expected);
        // then
        assertThat(actual.name()).isEqualTo(expected.name());
        assertThat(actual.description()).isEqualTo(expected.description());
    }

    @Test
    void should_delete_genre() {
        // given
        GenreResponseDto expected = genreFacade.saveGenre(new GenreRequestDto("test", "test"));
        // when
        genreFacade.removeGenreById(expected.id());
        // then
        assertThat(genreFacade.findAll()).hasSize(0);
    }

    @Test
    void should_update_genre() {
        // given
        GenreRequestDto genreToSave = new GenreRequestDto("test", "test");
        GenreResponseDto genreResponseDto = genreFacade.saveGenre(genreToSave);
        GenreRequestDto updatedGenre = new GenreRequestDto("test1", "test1");
        // when
        GenreResponseDto actual = genreFacade.updateGenre(genreResponseDto.id(), updatedGenre);
        // then
        assertThat(actual.name()).isEqualTo(updatedGenre.name());
        assertThat(actual.description()).isEqualTo(updatedGenre.description());
    }

    @Test
    void should_throw_exception_if_genre_not_found() {
        // given
        Long id = 1L;
        // when
        Throwable thrown = catchThrowable(() -> genreFacade.findById(id));
        // then
        assertThat(thrown).isInstanceOf(GenreNotFoundException.class)
                .hasMessageContaining("Genre with this id does not exist: %s", id);
    }

    @Test
    void should_throw_exception_if_genre_with_given_name_already_exists() {
        // given
        GenreRequestDto genreToSave = new GenreRequestDto("test", "test");
        GenreResponseDto genreResponseDto = genreFacade.saveGenre(genreToSave);
        // when
        Throwable thrown = catchThrowable(() -> genreFacade.saveGenre(genreToSave));
        // then
        assertThat(thrown).isInstanceOf(GenreAlreadyExistsException.class)
                .hasMessageContaining("Genre with this name already exists: %s", genreResponseDto.name());
    }
}