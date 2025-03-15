package pl.pkasiewicz.movieclub.domain.comment;

import org.junit.jupiter.api.Test;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentRequestDto;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentResponseDto;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class CommentFacadeTest {

    private final CommentFacade commentFacade = new CommentFacade(
            new InMemoryCommentRepository()
    );

    @Test
    void should_create_comment() {
        // given
        UserDto author = new UserDto(1L, "test", "test", "test", null, null);
        MovieDto movie = new MovieDto(1L, "test", "test", 1234, "test", "test", "test", "test", null, null, null);
        CommentRequestDto expected = new CommentRequestDto(author, movie, "test");
        // when
        CommentResponseDto actual = commentFacade.save(expected);
        // then
        assertAll(
                () -> assertThat(actual.message()).isEqualTo(expected.message()),
                () -> assertThat(actual.author()).isEqualTo(expected.author()),
                () -> assertThat(actual.movie()).isEqualTo(expected.movie())
        );
    }

    @Test
    void should_return_all_comments_for_given_movie_by_id() {
        // given
        UserDto author = new UserDto(1L, "test", "test", "test", null, null);
        MovieDto movie = new MovieDto(1L, "test", "test", 1234, "test", "test", "test", "test", null, null, null);
        List<CommentRequestDto> comments = List.of(
                new CommentRequestDto(author, movie, "test1"),
                new CommentRequestDto(author, movie, "test2"),
                new CommentRequestDto(author, movie, "test3")
        );
        comments.forEach(commentFacade::save);

        // when
        List<CommentResponseDto> actualComments = commentFacade.findAllCommentsByMovieId(movie.id());

        // then
        assertThat(actualComments).hasSize(3);
        List<String> expectedMessages = List.of("test1", "test2", "test3");
        assertThat(actualComments.stream().map(CommentResponseDto::message).toList())
                .containsExactlyInAnyOrderElementsOf(expectedMessages);
    }
}