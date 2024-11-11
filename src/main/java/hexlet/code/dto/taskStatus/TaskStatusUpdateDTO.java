package hexlet.code.dto.taskStatus;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@NoArgsConstructor
public class TaskStatusUpdateDTO {

    @NotNull
    JsonNullable<Long> id;

    @Size(min = 1)
    @NotNull
    JsonNullable<String> name;

    @Size(min = 1)
    @NotNull
    JsonNullable<String> slug;
}
