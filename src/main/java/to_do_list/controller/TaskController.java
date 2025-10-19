package to_do_list.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to_do_list.dto.task.*;
import to_do_list.service.TaskService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskCreateResponse> addTask(@RequestBody TaskCreateRequest request) {
        TaskCreateResponse result = taskService.register(request);
        log.info("Task created successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskGetResponse>> tasks() {
        List<TaskGetResponse> result = taskService.getAll();
        log.info("Tasks getAll successfully and result.size is {}", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping(params = "author")//쿼리 파라메터 /tasks?Author="kim"
    public ResponseEntity<List<TaskGetResponse>> tasksByAuthor(@RequestParam String author) {
        log.info("tasksByAuthor successfully and author is {}", author);
        List<TaskGetResponse> result = taskService.getAll(author);
        log.info("Tasks getAllByAuhor successfully and result.size is {}", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<TaskGetResponse> task(@PathVariable Long taskId) {
        TaskGetResponse result = taskService.getOne(taskId);
        log.info("Task getOne successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskUpdateResponse> editTask(@PathVariable Long taskId, @RequestBody TaskUpdateRequest taskUpdateRequest) {
        TaskUpdateResponse result = taskService.update(taskId, taskUpdateRequest);
        log.info("Task edit successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId, @RequestBody TaskDeleteRequest taskDeleteRequest) {
        taskService.remove(taskId, taskDeleteRequest);
        log.info("Task delete successfully and result is {}", taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
