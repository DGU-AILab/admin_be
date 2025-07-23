package DGU_AI_LAB.admin_be.domain.approval.dto;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.GroupType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApprovalResponse(
        String username,
        ResourceGroupDTO resourceGroup,
        Integer volumeSize,
        LocalDateTime approvedAt
) {
    @Builder
    public record ResourceGroupDTO(
            Long id,
            String name,
            GroupType groupType,
            Integer groupNumber
    ) {}
}
