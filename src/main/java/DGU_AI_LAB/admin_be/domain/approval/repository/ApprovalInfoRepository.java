package DGU_AI_LAB.admin_be.domain.approval.repository;

import DGU_AI_LAB.admin_be.domain.approval.entity.ApprovalInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalInfoRepository extends JpaRepository<ApprovalInfo, Long> {
}
