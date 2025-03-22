package pl.pkasiewicz.movieclub.domain.rating;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.movieclub.domain.movie.MovieFacade;
import pl.pkasiewicz.movieclub.domain.movie.MovieMapper;
import pl.pkasiewicz.movieclub.domain.movie.dto.MovieResponseDto;
import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;
import pl.pkasiewicz.movieclub.domain.user.UserFacade;
import pl.pkasiewicz.movieclub.domain.user.dto.UserDto;

@AllArgsConstructor
public class RatingFacade {

    private final RatingRepository ratingRepository;
    private final UserFacade userFacade;
    private final MovieFacade movieFacade;

    public RatingDto addOrUpdateRating(String username, Long movieId, Integer rating) {
        UserDto user = userFacade.findByUsername(username);
        MovieResponseDto movie = movieFacade.findMovieById(movieId);
        Rating ratingToSaveOrUpdate = ratingRepository.findByUser_UsernameAndMovie_Id(username, movieId)
                .map(existingRating -> new Rating(
                        existingRating.id(),
                        existingRating.movie(),
                        existingRating.user(),
                        rating
                ))
                .orElseGet(() -> Rating.builder()
                        .rating(rating)
                        .user(user)
                        .movie(MovieMapper.mapFromMovieResponseDtoToMovieDto(movie))
                        .build());
        Rating savedRating = ratingRepository.save(ratingToSaveOrUpdate);
        return RatingMapper.mapToRatingDto(savedRating);
    }

    public Integer getUserRatingForMovie(String username, Long movieId) {
        return ratingRepository.findByUser_UsernameAndMovie_Id(username, movieId)
                .map(Rating::rating)
                .orElse(0);
    }
}
