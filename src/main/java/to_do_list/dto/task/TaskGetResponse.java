package to_do_list.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;
import to_do_list.domain.entity.Comment;
import to_do_list.dto.comment.CommentGetResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class TaskGetResponse {
    private final Long id;
    private List<CommentGetResponse> comments;
    private final LocalDate deadline;
    private final Project project;
    private final TaskCategory taskCategory;
    private final String author;
    private final TaskStatus taskStatus;
    private final String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

}
