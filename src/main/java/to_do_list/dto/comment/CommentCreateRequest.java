package to_do_list.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentCreateRequest {
    @NotNull
    private String password;
    @NotNull
    private String author;
    @NotNull
    @Size(max = 100, message = "댓글은 최대 100자까지 입력 가능합니다.")
    private String description;
}
