package hexlet.code.controller;

import hexlet.code.dto.taskStatus.TaskStatusCreateDTO;
import hexlet.code.dto.taskStatus.TaskStatusDTO;
import hexlet.code.dto.taskStatus.TaskStatusUpdateDTO;
import hexlet.code.service.TaskStatusService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/task_statuses")
public class TaskStatusesController {
    private TaskStatusService service;

    @GetMapping()
    public ResponseEntity<List<TaskStatusDTO>> getAll() {
        var statuses = service.getAll();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(statuses.size()))
                .body(statuses);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public TaskStatusDTO create(@Valid @RequestBody TaskStatusCreateDTO statusData) {
        return service.create(statusData);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TaskStatusDTO update(@RequestBody @Valid TaskStatusUpdateDTO statusData, @PathVariable Long id) {
        return service.update(statusData, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}