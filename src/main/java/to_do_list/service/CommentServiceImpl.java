package to_do_list.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import to_do_list.domain.entity.Comment;
import to_do_list.domain.entity.Task;
import to_do_list.dto.comment.*;
import to_do_list.exception.InvalidPasswordException;
import to_do_list.exception.NotFoundException;
import to_do_list.exception.ResourceLimitExceededException;
import to_do_list.repository.CommentRepository;
import to_do_list.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    public static final int RESOURCE_LIMIT = 10;  //댓글 갯수 상한
    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public CommentCreateResponse register(Long taskId, CommentCreateRequest requset) {
        Task task = validateCommentCreationLimit(taskId);
        Comment comment = new Comment(requset.getPassword(), requset.getDescription(), requset.getAuthor());
        comment.affectTask(task);
        Comment save = commentRepository.save(comment);
        return new CommentCreateResponse(save.getId(), save.getDescription(), save.getAuthor());
    }

    @Override
    @Transactional(readOnly = true)
    public CommentGetResponse getOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NotFoundException(commentId, "존재하지 않는 id")
        );
        return new CommentGetResponse(commentId, comment.getAuthor(), comment.getDescription(), comment.getCreatedAt(), comment.getModifiedAt());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentGetResponse> getAll() {
        return commentRepository.findAll().stream()
                .map(comment -> new CommentGetResponse(
                        comment.getId(),
                        comment.getAuthor(),
                        comment.getDescription(),
                        comment.getCreatedAt(),
                        comment.getModifiedAt()
                )).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentUpdateResponse update(Long commentId, CommentUpdateRequest request) {
        Comment comment = getCommentWithPasswordCheck(commentId, request.getPassword());
        comment.update(request.getDescription(), request.getAuthor()); // Comment 엔티티에 update(description) 메서드 있다고 가정
        return new CommentUpdateResponse(
                comment.getId(),
                comment.getAuthor(),
                comment.getDescription()
        );
    }

    @Override
    @Transactional
    public void remove(Long commentId, CommentDeleteRequest request) {
        Comment comment = getCommentWithPasswordCheck(commentId, request.getPassword());
        commentRepository.delete(comment);
    }

    private Comment getCommentWithPasswordCheck(Long commentId, String requestPassword) {
        Comment comment = commentRepository.findById(commentId) //해당 id 가 존재하지 않는경우
                .orElseThrow(() -> new NotFoundException(commentId, "해당 ID의 댓글이 존재하지 않습니다."));
        if (!requestPassword.equals(comment.getPassword())) {  //
            throw new InvalidPasswordException(requestPassword, "비밀번호가 일치하지 않습니다.");
        }
        return comment;
    }

    private Task validateCommentCreationLimit(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new NotFoundException(taskId, "존재하지 않는 id"));
        if (task.getComments().size() > RESOURCE_LIMIT) {
            throw new ResourceLimitExceededException("객체 생성 불가");
        }
        return task;
    }
}
