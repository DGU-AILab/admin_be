package DGU_AI_LAB.admin_be.domain.resourceGroups.repository;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceGroupRepository extends JpaRepository<ResourceGroup, Long> {
}