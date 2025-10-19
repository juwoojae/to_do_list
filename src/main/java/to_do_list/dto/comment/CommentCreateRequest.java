package to_do_list.dto.comment;

import lombok.Getter;

@Getter
public class CommentCreateRequest {
    private String password;
    private String author;
    private String description;
}
