package pl.pkasiewicz.movieclub.domain.rating;

import org.junit.jupiter.api.Test;
import pl.pkasiewicz.movieclub.domain.movie.InMemoryMovieRepository;
import pl.pkasiewicz.movieclub.domain.movie.MovieFacade;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieRequestDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;
import pl.pkasiewicz.movieclub.domain.user.InMemoryUserRepository;
import pl.pkasiewicz.movieclub.domain.user.UserFacade;
import pl.pkasiewicz.movieclub.domain.user.dto.RegisterUserDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class RatingFacadeTest {

    private final UserFacade userFacade = new UserFacade(new InMemoryUserRepository());
    private final MovieFacade movieFacade = new MovieFacade(new InMemoryMovieRepository());
    private final RatingFacade ratingFacade = new RatingFacade(
            new InMemoryRatingRepository(),
            userFacade,
            movieFacade
    );

    @Test
    void should_return_user_rating_for_movie() {
        // given
        RegisterUserDto user = new RegisterUserDto("test1", "test1", "test1");
        MovieRequestDto movie = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);

        userFacade.registerUser(user);
        movieFacade.addMovie(movie);

        ratingFacade.addOrUpdateRating(user.username(), 1L, 4);

        // when
        Integer actual = ratingFacade.getUserRatingForMovie(user.username(), 1L);

        // then
        assertThat(actual).isEqualTo(4);
    }

    @Test
    void should_add_or_update_rating_for_movie() {
        // given
        RegisterUserDto user = new RegisterUserDto("test", "test", "test");
        MovieRequestDto movie = new MovieRequestDto("test", "test", 1234, "test", "test", "test", "test", null);

        userFacade.registerUser(user);
        movieFacade.addMovie(movie);

        // when
        RatingDto actual = ratingFacade.addOrUpdateRating(user.username(), 1L, 5);

        // then
        assertAll(
                () -> assertThat(actual.rating()).isEqualTo(5),
                () -> assertThat(actual.movie().id()).isEqualTo(1L),
                () -> assertThat(actual.user().username()).isEqualTo("test")
        );
    }
}