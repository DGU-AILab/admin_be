package DGU_AI_LAB.admin_be.domain.resourceGroups.dto;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.GroupType;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import lombok.Builder;

@Builder
public record ResourceGroupResponse(
        Long id,
        String name,
        GroupType groupType,
        String description
) {
    public static ResourceGroupResponse fromEntity(ResourceGroup group) {
        return ResourceGroupResponse.builder()
                .id(group.getResourceGroupId())
                .name(group.getResourceGroupName())
                .groupType(group.getGroupType())
                .description(group.getResourceGroupDescription())
                .build();
    }
}