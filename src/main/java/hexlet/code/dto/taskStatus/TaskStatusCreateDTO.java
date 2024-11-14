package hexlet.code.dto.taskStatus;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class TaskStatusCreateDTO {

    @Size(min = 1)
    @NotNull
    private String name;

    @Size(min = 1)
    @NotNull
    private String slug;
}

