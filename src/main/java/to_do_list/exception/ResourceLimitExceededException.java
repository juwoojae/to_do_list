package to_do_list.exception;

/**
 * 서버에서 제한한 리소스의 갯수를 넘어갈때 발생하는 예외
 */
public class ResourceLimitExceededException extends RuntimeException {
    public ResourceLimitExceededException(String message) {
        super(message);
    }
}
