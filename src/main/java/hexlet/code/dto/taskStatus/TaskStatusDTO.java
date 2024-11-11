package hexlet.code.dto.taskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskStatusDTO {
    private Long id;
    private String name;
    private String slug;
    private LocalDate createdAt;
}
