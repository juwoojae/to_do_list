package to_do_list.dto.task;

import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;

import java.time.LocalDate;

@Getter
public class TaskUpdateResponse {
    private final Long id;
    private final LocalDate deadline;
    private final Project project;
    private final TaskCategory taskCategory;
    private final String author;
    private final TaskStatus taskStatus;
    private final String description;

    public TaskUpdateResponse(Long id, LocalDate deadline, Project project, TaskCategory taskCategory, String author, TaskStatus taskStatus, String description) {
        this.id = id;
        this.deadline = deadline;
        this.project = project;
        this.taskCategory = taskCategory;
        this.author = author;
        this.taskStatus = taskStatus;
        this.description = description;
    }
}
