package to_do_list.dto.task;
import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;
import java.time.LocalDate;


@Getter
public class TaskUpdateRequest {
    private String password;
    private LocalDate deadline;
    private Project project;
    private TaskCategory taskCategory;
    private String author;
    private TaskStatus taskStatus;
    private String description;
}
