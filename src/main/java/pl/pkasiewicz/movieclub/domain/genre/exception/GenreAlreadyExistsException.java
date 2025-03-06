package pl.pkasiewicz.movieclub.domain.genre.exception;

public class GenreAlreadyExistsException extends RuntimeException {
    public GenreAlreadyExistsException(String message) {
        super(message);
    }
}
