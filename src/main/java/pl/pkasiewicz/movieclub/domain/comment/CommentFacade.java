package pl.pkasiewicz.movieclub.domain.comment;

import lombok.AllArgsConstructor;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentRequestDto;
import pl.pkasiewicz.movieclub.domain.comment.dto.CommentResponseDto;

import java.util.List;

@AllArgsConstructor
public class CommentFacade {

    private final CommentRepository commentRepository;

    public CommentResponseDto save(CommentRequestDto dto) {
        Comment savedComment = commentRepository.save(CommentMapper.mapToEntity(dto));
        return CommentMapper.mapToCommentResponseDto(savedComment);
    }

    public List<CommentResponseDto> findAllCommentsByMovieId(Long id) {
        return commentRepository.findAllByMovie_IdOrderByCreateDateDesc(id)
                .stream()
                .map(CommentMapper::mapToCommentResponseDto)
                .toList();
    }
}
