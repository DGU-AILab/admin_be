package DGU_AI_LAB.admin_be.domain.resourceGroups.dto;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.GroupType;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ResourceGroupCreateRequest(
        @NotBlank String name,
        @NotNull GroupType groupType,
        @NotNull @Min(0) Integer groupNumber,
        String description // optional
) {
    public ResourceGroup toEntity() {
        return ResourceGroup.builder()
                .resourceGroupName(name)
                .groupType(groupType)
                .groupNumber(groupNumber)
                .resourceGroupDescription(description)
                .build();
    }
}
