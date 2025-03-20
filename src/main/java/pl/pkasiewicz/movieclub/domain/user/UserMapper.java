package pl.pkasiewicz.movieclub.domain.user;

import pl.pkasiewicz.movieclub.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.movieclub.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

class UserMapper {

    static User mapToEntity(RegisterUserDto dto) {
        return User.builder()
                .username(dto.username())
                .email(dto.email())
                .password(dto.password())
                .build();
    }

    static RegistrationResultDto mapToRegistrationResultDto(User savedUser) {
        return RegistrationResultDto.builder()
                .id(savedUser.id())
                .username(savedUser.username())
                .email(savedUser.email())
                .password(savedUser.password())
                .build();
    }

    static UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.id())
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .ratings(user.ratings())
                .comments(user.comments())
                .build();
    }
}
