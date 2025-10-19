package to_do_list;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import to_do_list.domain.Project;
import to_do_list.domain.entity.Task;
import to_do_list.domain.entity.Comment;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;
import to_do_list.repository.CommentRepository;
import to_do_list.repository.TaskRepository;
import to_do_list.service.CommentService;
import to_do_list.service.TaskService;


import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    /**
     * 테스트 케이스 만들기
     */
    @PostConstruct
    public void init() {
        Task task1 = new Task(
                "test1",
                LocalDate.of(2025, 10, 20),
                Project.RIVERS,
                TaskCategory.CODE_REVIEW,
                "kim",
                TaskStatus.IN_PROGRESS,
                "테스트용 설명 1"
        );

        Task task2 = new Task(
                "test2",
                LocalDate.of(2025, 11, 1),
                Project.RIVERS,
                TaskCategory.MEETING,
                "lee",
                TaskStatus.COMPLETED,
                "테스트용 설명 2"
        );
        taskRepository.save(task1);
        taskRepository.save(task2);
        Comment comment1 = new Comment("test!", "첫 번째 댓글", "kim");
        Comment comment2 = new Comment("test@", "두 번째 댓글", "han");
        comment1.affectTask(task1);
        commentRepository.save(comment1);
        comment2.affectTask(task2);
        commentRepository.save(comment2);


    }
}
