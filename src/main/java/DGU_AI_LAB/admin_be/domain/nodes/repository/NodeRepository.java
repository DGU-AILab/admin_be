package DGU_AI_LAB.admin_be.domain.nodes.repository;

import DGU_AI_LAB.admin_be.domain.nodes.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByIsAvailableTrue();
}
