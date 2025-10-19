package to_do_list.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import to_do_list.domain.entity.Comment;
import to_do_list.dto.comment.*;
import to_do_list.dto.task.*;
import to_do_list.repository.CommentRepository;
import to_do_list.service.CommentService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentCreateResponse> addComment(@PathVariable Long taskId, @RequestBody CommentCreateRequest request) {
        CommentCreateResponse result = commentService.register(taskId, request);
        log.info("Comments created successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CommentGetResponse>> commnets() {
        List<CommentGetResponse> result = commentService.getAll();
        log.info("Comments getAll successfully and result.size is {}", result.size());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentGetResponse> task(@PathVariable Long commentId) {
        CommentGetResponse result = commentService.getOne(commentId);
        log.info("Comments getOne successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentUpdateResponse> editTask(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        CommentUpdateResponse result = commentService.update(commentId, commentUpdateRequest);
        log.info("Comments edit successfully and result is {}", result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long commentId, @RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.remove(commentId, commentDeleteRequest);
        log.info("Comments delete successfully and result is {}", commentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
