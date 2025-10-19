package to_do_list.service;

import to_do_list.dto.task.*;

import java.util.List;

public interface TaskService {
    //등록
    TaskCreateResponse register(TaskCreateRequest requset);
    //단일 조회
    TaskGetResponse getOne(Long taskId);

    //전체 조회
    //1. 작성자명으로 전체 조회하는 경우
    List<TaskGetResponse> getAll(String findAuthor);
    //2. 전체 조회
    List<TaskGetResponse> getAll();
    //갱신
    TaskUpdateResponse update(Long taskId, TaskUpdateRequest requset);
    //단일 제거
    void remove(Long taskId, TaskDeleteRequest request);
    //전체 제거
    void clear();
}
