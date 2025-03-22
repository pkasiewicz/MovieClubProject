package pl.pkasiewicz.movieclub.domain.rating;

import pl.pkasiewicz.movieclub.domain.rating.dto.RatingDto;

class RatingMapper {

    static RatingDto mapToRatingDto(Rating entity) {
        return RatingDto.builder()
                .id(entity.id())
                .user(entity.user())
                .movie(entity.movie())
                .rating(entity.rating())
                .build();
    }
}
