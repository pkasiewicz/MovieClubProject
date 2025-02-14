package pl.pkasiewicz.movieclub.domain.user;

import pl.pkasiewicz.movieclub.domain.user.exceptions.UserAlreadyExistsException;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

class InMemoryUserRepository implements UserRepository {

    Map<Long, User> database = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findByEmail(String email) {
        return database.values()
                .stream()
                .filter(user -> user.email().equals(email))
                .findFirst();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return database.values()
                .stream()
                .filter(user -> user.username().equals(username))
                .findFirst();
    }

    @Override
    public User save(User user) {
        if (database.values().stream().anyMatch(entity -> entity.username().equals(user.username())))
            throw new UserAlreadyExistsException("User with this username already exists: " + user.username());
        if (database.values().stream().anyMatch(entity -> entity.email().equals(user.email())))
            throw new UserAlreadyExistsException("User with this email already exists: " + user.email());
        Long id = ThreadLocalRandom.current().nextLong();
        User userToSave = User.builder()
                .id(id)
                .username(user.username())
                .email(user.email())
                .password(user.password())
                .build();
        database.put(id, userToSave);
        return userToSave;
    }
}
