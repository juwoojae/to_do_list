package to_do_list.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentUpdateResponse {
    private Long id;
    private String author;
    private String description;

}
