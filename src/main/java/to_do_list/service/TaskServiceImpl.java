package to_do_list.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import to_do_list.domain.entity.Task;
import to_do_list.dto.task.*;
import to_do_list.exception.InvalidPasswordException;
import to_do_list.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskCreateResponse register(TaskCreateRequest requset) {
        Task task = new Task(requset.getPassword(), requset.getDeadline(), requset.getProject(), requset.getTaskCategory(), requset.getAuthor(), requset.getTaskStatus(), requset.getDescription());
        Task save = taskRepository.save(task);
        return new TaskCreateResponse(save.getId()
                , save.getDeadline()
                , save.getProject()
                , requset.getTaskCategory()
                , save.getAuthor()
                , save.getTaskStatus()
                , save.getDescription());
    }

    @Override
    @Transactional(readOnly = true)
    public TaskGetResponse getOne(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 id")
        );
        return new TaskGetResponse(taskId
                , task.getDeadline()
                , task.getProject()
                , task.getTaskCategory()
                , task.getAuthor()
                , task.getTaskStatus()
                , task.getDescription()
                , task.getCreatedAt()
                , task.getModifiedAt());
    }

    /**
     * `작성자명`을 기준으로 등록된 일정 목록을 전부 조회
     * 1.`작성자명`은 조회 조건으로 포함될 수도 있고,
     * 2. 포함되지 않을 수도 있습니다.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaskGetResponse> getAll(String findAuthor) {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .filter(task -> task.getAuthor().equals(findAuthor)) // 작성자 필터
                .map(task -> new TaskGetResponse(
                        task.getId(),
                        task.getDeadline(),
                        task.getProject(),
                        task.getTaskCategory(),
                        task.getAuthor(),
                        task.getTaskStatus(),
                        task.getDescription(),
                        task.getCreatedAt(),
                        task.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskGetResponse> getAll() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskGetResponse(
                        task.getId(),
                        task.getDeadline(),
                        task.getProject(),
                        task.getTaskCategory(),
                        task.getAuthor(),
                        task.getTaskStatus(),
                        task.getDescription(),
                        task.getCreatedAt(),
                        task.getModifiedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskUpdateResponse update(Long taskId, TaskUpdateRequest request) {
        Task task = getTaskWithPasswordCheck(taskId, request.getPassword());
        task.update(
                request.getDeadline(),
                request.getProject(),
                request.getTaskCategory(),
                request.getAuthor(),
                request.getTaskStatus(),
                request.getDescription()
        );
        return new TaskUpdateResponse(task.getId(),
                task.getDeadline(),
                task.getProject(),
                task.getTaskCategory(),
                task.getAuthor(),
                task.getTaskStatus(),
                task.getDescription()
        );
    }

    @Override
    @Transactional
    public void remove(Long taskId, TaskDeleteRequest request) {
        Task task = getTaskWithPasswordCheck(taskId, request.getPassword());
        taskRepository.delete(task);
    }

    //데이터 정리용 (별도)
    @Override
    @Transactional
    public void clear() {
        taskRepository.deleteAll();
    }

    private Task getTaskWithPasswordCheck(Long taskId, String requestPassword) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalStateException("해당 ID의 Task가 존재하지 않습니다."));
        if (!requestPassword.equals(task.getPassword())) {
            throw new InvalidPasswordException("비밀번호가 일치하지 않습니다.");
        }
        return task;
    }
}
