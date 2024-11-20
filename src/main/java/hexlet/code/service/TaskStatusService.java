package hexlet.code.service;

import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;
import hexlet.code.mapper.TaskStatusMapper;
import hexlet.code.repository.TaskStatusRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskStatusService {

    private final TaskStatusRepository repository;
    private final TaskStatusMapper mapper;

    public List<TaskStatusDTO> getAll() {
        var statuses = repository.findAll();
        return mapper.map(statuses);
    }

    public TaskStatusDTO getById(Long id) {
        var status = repository.findById(id)
                .orElseThrow();
        return mapper.map(status);
    }

    public TaskStatusDTO create(TaskStatusCreateDTO statusData) {
        var status = mapper.map(statusData);
        repository.save(status);
        return mapper.map(status);
    }

    public TaskStatusDTO update(TaskStatusUpdateDTO statusData, Long id) {
        var status = repository.findById(id)
                .orElseThrow();
        mapper.map(statusData, status);
        repository.save(status);
        return mapper.map(status);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
