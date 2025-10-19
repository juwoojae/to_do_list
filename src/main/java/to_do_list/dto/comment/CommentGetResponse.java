package to_do_list.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentGetResponse {
    private Long id;
    private String author;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
