package DGU_AI_LAB.admin_be.domain.resourceGroups.service;

import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupCreateRequest;
import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupResponse;
import DGU_AI_LAB.admin_be.domain.resourceGroups.repository.ResourceGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResourceGroupService {

    private final ResourceGroupRepository resourceGroupRepository;

    public void createResourceGroup(ResourceGroupCreateRequest request) {
        resourceGroupRepository.save(request.toEntity());
    }

    public List<ResourceGroupResponse> getAllResourceGroups() {
        return resourceGroupRepository.findAll()
                .stream()
                .map(ResourceGroupResponse::fromEntity)
                .toList();
    }
}