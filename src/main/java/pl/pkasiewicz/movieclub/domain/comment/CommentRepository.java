package pl.pkasiewicz.movieclub.domain.comment;

import java.util.List;

public interface CommentRepository {
    Comment save(Comment comment);
    List<Comment> findAllByMovie_IdOrderByCreateDateDesc(Long id);
}
