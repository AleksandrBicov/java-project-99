package hexlet.code.dto.label;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LabelUpdateDTO {

    @NotNull
    @Size(min = 3, max = 1000)
    private JsonNullable<String> name;
}