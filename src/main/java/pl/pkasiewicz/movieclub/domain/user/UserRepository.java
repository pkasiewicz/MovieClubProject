package pl.pkasiewicz.movieclub.domain.user;

import java.util.Optional;

interface UserRepository {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    User save(User user);
}
