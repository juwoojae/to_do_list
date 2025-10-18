package to_do_list;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import to_do_list.domain.Project;
import to_do_list.domain.entity.Task;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;
import to_do_list.repository.TaskRepository;
import to_do_list.service.TaskService;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @PostConstruct
    public void init() {
        Task task1 = new Task(
                LocalDateTime.of(2025, 10, 20, 12, 0),
                Project.RIVERS,
                TaskCategory.CODE_REVIEW,
                "첫 번째 작업",
                TaskStatus.IN_PROGRESS,
                "테스트용 설명 1"
        );

        Task task2 = new Task(
                LocalDateTime.of(2025, 11, 1, 18, 30),
                Project.RIVERS,
                TaskCategory.MEETING,
                "두 번째 작업",
                TaskStatus.COMPLETED,
                "테스트용 설명 2"
        );
        taskRepository.save(task1);
        taskRepository.save(task2);
    }
}
