package to_do_list.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentGetResponse {
    private Long id;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentGetResponse(Long id, String author, String description, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.author = author;
        this.description = description;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
