package pl.pkasiewicz.movieclub.domain.comment;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryCommentRepository implements CommentRepository {

    private final Map<Long, Comment> database = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Comment save(Comment comment) {
        Long id = idGenerator.getAndIncrement();
        Comment commentToSave = Comment.builder()
                .id(id)
                .message(comment.message())
                .author(comment.author())
                .movie(comment.movie())
                .createDate(comment.createDate())
                .build();
        database.put(id, commentToSave);
        return commentToSave;
    }

    @Override
    public List<Comment> findAllByMovie_IdOrderByCreateDateDesc(Long id) {
        return database.values().stream()
                .filter(comment -> comment.movie().id().equals(id))
                .sorted((c1, c2) -> c2.createDate().compareTo(c1.createDate()))
                .collect(Collectors.toList());
    }
}
