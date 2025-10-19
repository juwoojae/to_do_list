package to_do_list.dto.comment;

import lombok.Getter;

@Getter
public class CommentCreateResponse {
    private Long id;
    private String author;
    private String description;

    public CommentCreateResponse(Long id, String author, String description) {
        this.id = id;
        this.author = author;
        this.description = description;
    }
}
