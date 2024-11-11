package hexlet.code.mapper;

import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;
import hexlet.code.model.TaskStatus;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        uses = { JsonNullableMapper.class },
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public abstract class TaskStatusMapper {

    public abstract List<TaskStatusDTO> map(List<TaskStatus> listStatuses);

    public abstract TaskStatus map(TaskStatusCreateDTO dtoCreate);

    public abstract TaskStatusDTO map(TaskStatus model);

    public abstract void map(TaskStatusUpdateDTO dtoUpdate, @MappingTarget TaskStatus taskStatus);
}