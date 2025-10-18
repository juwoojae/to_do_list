package to_do_list.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime deadline; //기한(날짜)

    @Enumerated(EnumType.STRING)
    private Project project; //프로젝트

    @Enumerated(EnumType.STRING)
    @Column(name="task_category")
    private TaskCategory taskCategory; //업무 구분

    private String name; //할일

    @Enumerated(EnumType.STRING)
    @Column(name="task_status")
    private TaskStatus taskStatus; //설명

    private String description; //설명

    //기한, 프로젝트, 업무구분, 할일, 상태, 설명
    public Task(LocalDateTime deadline, Project project, TaskCategory taskCategory, String name, TaskStatus taskStatus, String description) {
        this.deadline = deadline;
        this.project = project;
        this.taskCategory = taskCategory;
        this.name = name;
        this.taskStatus = taskStatus;
        this.description = description;
    }


    public void update(LocalDateTime deadline, Project project, TaskCategory taskCategory, String name, TaskStatus taskStatus, String description) {
        this.deadline = deadline;
        this.project = project;
        this.taskCategory = taskCategory;
        this.name = name;
        this.taskStatus = taskStatus;
        this.description = description;
    }
}
