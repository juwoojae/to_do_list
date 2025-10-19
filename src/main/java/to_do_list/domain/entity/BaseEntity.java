package to_do_list.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import to_do_list.domain.TaskCategory;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
    /**
     * 수정된 시간을 관리하는 리스트
     */
//    @Transient //객체 내부적으로만 관리 이것도 1:N매핑으로 엔티티 관리를 해주려고 했으나 시간이 없어서 이렇게 구현
//    private List<LocalDateTime> modifieHistory;
}
