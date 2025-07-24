package DGU_AI_LAB.admin_be.domain.nodes.service;


import DGU_AI_LAB.admin_be.domain.nodes.dto.request.NodeCreateRequest;
import DGU_AI_LAB.admin_be.domain.nodes.dto.response.NodeResponse;
import DGU_AI_LAB.admin_be.domain.nodes.entity.Node;
import DGU_AI_LAB.admin_be.domain.nodes.repository.NodeRepository;
import DGU_AI_LAB.admin_be.domain.resourceGroups.repository.ResourceGroupRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NodeService {

    private final NodeRepository nodeRepository;
    private final ResourceGroupRepository resourceGroupRepository;

    public void createNode(NodeCreateRequest request) {
        var group = resourceGroupRepository.findById(request.resourceGroupId())
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_GROUP_NOT_FOUND));

        Node node = request.toEntity(group);
        nodeRepository.save(node);
    }

    public List<NodeResponse> getAllNodes() {
        return nodeRepository.findAll()
                .stream()
                .map(NodeResponse::fromEntity)
                .toList();
    }

    public NodeResponse getNodeById(Long id) {
        Node node = nodeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NODE_NOT_FOUND));
        return NodeResponse.fromEntity(node);
    }

}