package DGU_AI_LAB.admin_be.domain.approval.dto.response;

import DGU_AI_LAB.admin_be.domain.approval.entity.Approval;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.GroupType;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApprovalResponseDTO(
        String username,
        ResourceGroupDTO resourceGroup,
        Integer volumeSize,
        LocalDateTime approvedAt
) {
    @Builder
    public record ResourceGroupDTO(
            Long id,
            String username,
            GroupType groupType,
            Integer groupNumber
    ) {}

    public static ApprovalResponseDTO fromEntity(Approval approval) {
        var group = approval.getResourceGroup();
        return ApprovalResponseDTO.builder()
                .username(approval.getUser().getUsername())
                .volumeSize(approval.getVolumeSize())
                .approvedAt(approval.getCreatedAt())
                .resourceGroup(
                        ResourceGroupDTO.builder()
                                .id(group.getResourceGroupId())
                                .username(group.getResourceGroupName())
                                .groupType(group.getGroupType())
                                .groupNumber(group.getGroupNumber())
                                .build()
                )
                .build();
    }

}
