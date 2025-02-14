package pl.pkasiewicz.movieclub.domain.user;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import pl.pkasiewicz.movieclub.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.movieclub.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;
import pl.pkasiewicz.movieclub.domain.user.exceptions.UserAlreadyExistsException;
import pl.pkasiewicz.movieclub.domain.user.exceptions.UserNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;

class UserFacadeTest {

    UserFacade userFacade = new UserFacade(
            new InMemoryUserRepository()
    );

    @Test
    void should_return_user_by_username() {
        // given
        RegisterUserDto expected = new RegisterUserDto("someUser", "example@example.com", "somePass");
        userFacade.registerUser(expected);
        // when
        UserDto actual = userFacade.findByUsername(expected.username());
        // then
        assertThat(actual).isNotNull();
        assertThat(actual.username()).isEqualTo(expected.username());
    }

    @Test
    void should_register_user() {
        // given
        RegisterUserDto expected = new RegisterUserDto("someUser", "example@example.com", "somePass");
        // when
        RegistrationResultDto actual = userFacade.registerUser(expected);
        // then
        assertThat(actual).isNotNull();
        assertAll(
                () -> assertThat(actual.username()).isEqualTo(expected.username()),
                () -> assertThat(actual.email()).isEqualTo(expected.email()),
                () -> assertThat(actual.password()).isEqualTo(expected.password())
        );
    }

    @Test
    void should_throw_exception_if_username_already_exists() {
        // given
        RegisterUserDto user1 = new RegisterUserDto("someUser", "example1@example.com", "somePass");
        RegisterUserDto user2 = new RegisterUserDto("someUser", "example2@example.com", "somePass");
        userFacade.registerUser(user1);
        // when
        Throwable thrown = catchThrowable(() -> userFacade.registerUser(user2));
        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("User with this username already exists: " + user2.username());
    }

    @Test
    void should_throw_exception_if_email_already_exists() {
        // given
        RegisterUserDto user1 = new RegisterUserDto("someUser1", "example@example.com", "somePass");
        RegisterUserDto user2 = new RegisterUserDto("someUser2", "example@example.com", "somePass");
        userFacade.registerUser(user1);
        // when
        Throwable thrown = catchThrowable(() -> userFacade.registerUser(user2));
        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessageContaining("User with this email already exists: " + user2.email());
    }

    @Test
    void should_throw_exception_if_user_not_found() {
        // given && when
        Throwable thrown = catchThrowable(() -> userFacade.findByUsername("someUser"));
        // then
        AssertionsForClassTypes.assertThat(thrown)
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining("User does not exist");
    }
}