package to_do_list.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import to_do_list.domain.entity.Task;
import to_do_list.dto.*;
import to_do_list.repository.TaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    @Transactional
    public TaskCreateResponse register(TaskCreateRequset requset) {
        Task task = new Task(requset.getDeadline(), requset.getProject(), requset.getTaskCategory(), requset.getName(), requset.getTaskStatus(), requset.getDescription());
        Task save = taskRepository.save(task);
        return new TaskCreateResponse(save.getId()
                , save.getDeadline()
                , save.getProject()
                , requset.getTaskCategory()
                , save.getName()
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
                , task.getName()
                , task.getTaskStatus()
                , task.getDescription());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskGetResponse> getAll() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskGetResponse> responses = new ArrayList<>();
        tasks.forEach(task -> responses.add(new TaskGetResponse(task.getId()
                , task.getDeadline()
                , task.getProject()
                , task.getTaskCategory()
                , task.getName()
                , task.getTaskStatus()
                , task.getDescription())));
        return responses;
    }

    @Override
    @Transactional
    public TaskUpdateResponse update(Long taskId, TaskUpdateRequest requset) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 id")
        );
        task.update(requset.getDeadline(), requset.getProject(), requset.getTaskCategory(), requset.getName(), requset.getTaskStatus(), requset.getDescription());
        return new TaskUpdateResponse(taskId, task.getDeadline()
                , task.getProject()
                , task.getTaskCategory()
                , task.getName()
                , task.getTaskStatus()
                , task.getDescription());
    }

    @Override
    @Transactional
    public void remove(Long taskId) {
        boolean existence = taskRepository.existsById(taskId);
        if (!existence) {
            throw new IllegalStateException("존재하지 않는 id");
        }
        taskRepository.deleteById(taskId);
    }
    //데이터 정리용 (별도)
    @Override
    @Transactional
    public void clear() {
        taskRepository.deleteAll();
    }
}
