package hexlet.code.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private Long id;

    private String title;

    private int index;

    private String content;


    private String slug;

    private Long assigneeId;

    private Set<Long> taskLabelIds;

    private LocalDate createdAt;
}