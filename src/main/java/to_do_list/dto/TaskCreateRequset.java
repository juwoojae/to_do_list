package to_do_list.dto;

import lombok.Getter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;

import java.time.LocalDateTime;

@Getter
public class TaskCreateRequset {
   private LocalDateTime deadline;
   private Project project;
   private TaskCategory taskCategory;
   private String name;
   private TaskStatus taskStatus;
   private String description;
}
