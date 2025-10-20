package to_do_list.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * 전체 예외처리 컨트롤러
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 2차 서버 검증 - beanValidation 에 대한 400 를 포함한 HTTP 메세지 전달
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    /**
     * 비밀 번호 오류
     * 생각해보니 틀린 비밀 번호도 API 에 넣지 않는게 좋을거 같다
     * 비밀번호 오류 - 401 (인증 실패)
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<String> handlePasswordErrors(InvalidPasswordException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    /**
     * 댓글 제한 상한 오류(10개)
     * 리소스 제한 초과 문제 403 (권한 충돌)
     */
    @ExceptionHandler(ResourceLimitExceededException.class)
    public ResponseEntity<String> handleResourceLimitErrors(ResourceLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    /**
     * id 에 대한 Entity 찾기 실패 오류
     * NotFound - 404
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundErrors(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}