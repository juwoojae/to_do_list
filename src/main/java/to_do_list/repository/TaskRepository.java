package to_do_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import to_do_list.domain.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
