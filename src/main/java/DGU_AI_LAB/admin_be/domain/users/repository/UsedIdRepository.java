package DGU_AI_LAB.admin_be.domain.users.repository;

import DGU_AI_LAB.admin_be.domain.users.entity.UsedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsedIdRepository extends JpaRepository<UsedId,Long> {
    boolean existsById(Long id);
}