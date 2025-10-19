package to_do_list.service;

import to_do_list.domain.entity.Comment;
import to_do_list.dto.comment.*;

import java.util.List;

public interface CommentService {
    CommentCreateResponse register(CommentCreateRequest requset, Long TaskId);

    CommentGetResponse getOne(Long CommentId);

    List<CommentGetResponse> getAll();

    CommentUpdateResponse update(Long commentId, CommentUpdateRequest request);

    void remove(Long commentId, CommentDeleteRequest request);
}
