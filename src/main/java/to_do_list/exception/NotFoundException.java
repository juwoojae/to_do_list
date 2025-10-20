package to_do_list.exception;

/**
 * 해당 id 에 대한 Entity 가 존재하지 않는 경우
 */
public class NotFoundException extends RuntimeException {

    private final Long id;


    public NotFoundException(Long id, String message) {
        this.id = id;
        super(message);
    }

    public Long getId() {
        return id;
    }
}
