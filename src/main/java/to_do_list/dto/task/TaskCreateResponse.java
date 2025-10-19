package to_do_list.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class TaskCreateResponse {
    private final Long id;
    private final LocalDate deadline;
    private final Project project;
    private final TaskCategory taskCategory;
    private final String author;
    private final String title;
    private final TaskStatus taskStatus;
    private final String description;

}
