package hexlet.code.dto.taskStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@NoArgsConstructor
public class TaskStatusUpdateDTO {

    @NotNull
    private JsonNullable<Long> id;

    @Size(min = 1)
    @NotNull
    private JsonNullable<String> name;

    @Size(min = 1)
    @NotNull
    private JsonNullable<String> slug;
}
