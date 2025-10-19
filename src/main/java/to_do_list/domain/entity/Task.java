package to_do_list.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import to_do_list.domain.Project;
import to_do_list.domain.TaskCategory;
import to_do_list.domain.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Setter
@Getter
@Entity
@Table(name = "tasks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Task extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @OneToMany(mappedBy = "task")//누구랑 매핑되어있는지
    private List<Comment> comments = new ArrayList<>(); //주인의 반대편

    private String password;

    private LocalDate deadline; //기한(날짜)

    @Enumerated(EnumType.STRING)
    private Project project; //프로젝트

    @Enumerated(EnumType.STRING)
    @Column(name = "task_category")
    private TaskCategory taskCategory; //업무 구분

    @Column(length = 30, nullable = false)
    private String author; //작성자

    private String title;  //일정 제목

    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus; //설명

    @Lob
    @Column(length = 200, nullable = true)
    private String description; //설명

    //비밀번호, 기한, 프로젝트, 업무구분, 작성자명, 일정 내용, 상태, 설명

    public Task(String password, LocalDate deadline, Project project, TaskCategory taskCategory, String author, String title, TaskStatus taskStatus, String description) {
        this.password = password;
        this.deadline = deadline;
        this.project = project;
        this.taskCategory = taskCategory;
        this.author = author;
        this.title = title;
        this.taskStatus = taskStatus;
        this.description = description;
    }

    //패스워드 는 수정할수 있으면 안됨
    public void update(LocalDate deadline, Project project, TaskCategory taskCategory, String author, String title, TaskStatus taskStatus, String description) {
        this.deadline = deadline;
        this.project = project;
        this.taskCategory = taskCategory;
        this.author = author;
        this.title = title;
        this.taskStatus = taskStatus;
        this.description = description;
    }
}
