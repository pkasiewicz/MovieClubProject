package pl.pkasiewicz.movieclub.domain.user;

import lombok.Builder;

@Builder
record User(
        Long id,
        String username,
        String email,
        String password
) {
}
