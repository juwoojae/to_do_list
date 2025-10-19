package to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import to_do_list.domain.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
