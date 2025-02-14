package pl.pkasiewicz.movieclub.domain.user;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.movieclub.domain.user.dto.RegisterUserDto;
import pl.pkasiewicz.movieclub.domain.user.dto.RegistrationResultDto;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;
import pl.pkasiewicz.movieclub.domain.user.exceptions.UserNotFoundException;

@AllArgsConstructor
public class UserFacade {

    public static final String USER_DOES_NOT_EXIST = "User does not exist";
    private final UserRepository userRepository;

    public RegistrationResultDto registerUser(RegisterUserDto dto) {
        User savedUser = userRepository.save(UserMapper.mapToEntity(dto));
        return UserMapper.mapToRegistrationResultDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserMapper::mapToDto)
                .orElseThrow(() -> new UserNotFoundException(USER_DOES_NOT_EXIST));
    }
}
