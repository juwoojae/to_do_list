package to_do_list.dto.task;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;
import java.time.LocalDate;


@Getter
public class TaskUpdateRequest {
    @NotNull
    private String password;
    private LocalDate deadline;
    private Project project;
    private TaskCategory taskCategory;
    @NotNull
    private String author;
    @NotNull
    @Size(max = 30)
    private String title;
    private TaskStatus taskStatus;
    @NotNull
    @Size(max = 200)
    private String description;
}
