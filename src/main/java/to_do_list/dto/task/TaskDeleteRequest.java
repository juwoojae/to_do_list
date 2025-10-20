package to_do_list.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TaskDeleteRequest {
    @NotNull
    private String password;
}
