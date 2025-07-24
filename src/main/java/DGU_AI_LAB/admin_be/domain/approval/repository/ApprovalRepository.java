package DGU_AI_LAB.admin_be.domain.approval.repository;

import DGU_AI_LAB.admin_be.domain.approval.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApprovalRepository extends JpaRepository<Approval, Long> {
    Optional<Approval> findFirstByUserNameAndApprovedIsTrueOrderByCreatedAtDesc(String username);
}