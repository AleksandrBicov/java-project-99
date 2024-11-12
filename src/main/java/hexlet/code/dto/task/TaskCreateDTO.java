package hexlet.code.dto.task;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

import java.util.Set;

@Getter
@Setter
public class TaskCreateDTO {
    @NotNull
    @Size(min = 1)
    private String title;

    private JsonNullable<Integer> index;

    private JsonNullable<String> content;

    @NotNull
    private String slug;

    private JsonNullable<Long> assigneeId;

    private JsonNullable<Set<Long>> taskLabelIds;
}