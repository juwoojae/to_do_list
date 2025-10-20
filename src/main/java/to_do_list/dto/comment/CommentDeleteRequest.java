package to_do_list.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentDeleteRequest {
    @NotNull
    private String password;
}
