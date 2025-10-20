package to_do_list.exception;

/**
 * 비밀번호가 틀렸을때 던지는 예외
 */
public class InvalidPasswordException extends RuntimeException {

    private final String invalidPassword;

    public InvalidPasswordException(String invalidPassword, String message) {
        this.invalidPassword = invalidPassword;
        super(message);
    }

    public String getInvalidPassword() {
        return invalidPassword;
    }
}
