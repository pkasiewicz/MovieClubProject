package pl.pkasiewicz.movieclub.domain.comment;

import pl.pkasiewicz.movieclub.domain.comment.dto.CommentRequestDto;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentResponseDto;

import java.time.LocalDateTime;

public class CommentMapper {

    static Comment mapToEntity(CommentRequestDto dto) {
        return Comment.builder()
                .message(dto.message())
                .author(dto.author())
                .movie(dto.movie())
                .createDate(LocalDateTime.now())
                .build();
    }

    public static CommentResponseDto mapToCommentResponseDto(Comment entity) {
        return CommentResponseDto.builder()
                .id(entity.id())
                .message(entity.message())
                .author(entity.author())
                .movie(entity.movie())
                .createDate(entity.createDate())
                .build();
    }
}
