package DGU_AI_LAB.admin_be.domain.nodes;

import DGU_AI_LAB.admin_be.domain.resourceGroups.repository.ResourceGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NodeService {

    private final NodeRepository nodeRepository;
    private final ResourceGroupRepository resourceGroupRepository;

    public void createNode(NodeCreateRequest request) {
        var group = resourceGroupRepository.findById(request.resourceGroupId())
                .orElseThrow(() -> new IllegalArgumentException("리소스 그룹을 찾을 수 없습니다."));

        Node node = request.toEntity(group);
        nodeRepository.save(node);
    }
}