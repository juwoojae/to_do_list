package to_do_list.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to_do_list.dto.*;
import to_do_list.service.TaskService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskCreateResponse> addTask(@RequestBody TaskCreateRequset requset) {
        TaskCreateResponse result = taskService.register(requset);
        log.info("Task created successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskGetResponse>> tasks() {
        List<TaskGetResponse> result = taskService.getAll();
        log.info("Tasks getAll successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskGetResponse> task(@PathVariable("taskId") Long taskId) {
        TaskGetResponse result = taskService.getOne(taskId);
        log.info("Task getOne successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskUpdateResponse> editTask(@PathVariable("taskId") Long taskId, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskUpdateResponse result = taskService.update(taskId, taskUpdateRequest);
        log.info("Task edit successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.remove(taskId);
        log.info("Task delete successfully and result is {}", taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
