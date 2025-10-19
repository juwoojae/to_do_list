package to_do_list.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne //N to 1
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String author;

    @Lob
    @Column(length = 100, nullable = false)
    private String description;

    public Comment(String password, String description, String author) {
        this.password = password;
        this.description = description;
        this.author = author;
    }

    //비밀 번호는 업데이트 하면 안됨
    public void update(String description, String author) {
        this.description = description;
        this.author = author;
    }

    //편의 메서드
    public void affectTask(Task task) {
        this.task = task;
        task.getComments().add(this);
    }
}
