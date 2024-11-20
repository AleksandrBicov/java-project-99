package hexlet.code.service;

import hexlet.code.filtration.FiltrationTask;
import hexlet.code.dto.task.ParamsDTO;
import hexlet.code.dto.task.TaskCreateDTO;
import hexlet.code.dto.task.TaskDTO;
import hexlet.code.dto.task.TaskUpdateDTO;
import hexlet.code.mapper.TaskMapper;
import hexlet.code.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;
    private final FiltrationTask taskSpecification;

    public List<TaskDTO> getAll(ParamsDTO params) {
        var spec = taskSpecification.build(params);
        var tasks = repository.findAll(spec);
        return tasks.stream()
                .map(taskMapper::map)
                .toList();
    }

    public TaskDTO getById(Long id) {
        var task = repository.findById(id)
                .orElseThrow();
        return taskMapper.map(task);
    }

    public TaskDTO create(TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        repository.save(task);
        return taskMapper.map(task);
    }

    public TaskDTO update(TaskUpdateDTO taskData, Long id) {
        var task = repository.findById(id)
                .orElseThrow();
        taskMapper.update(taskData, task);
        repository.save(task);
        return taskMapper.map(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
