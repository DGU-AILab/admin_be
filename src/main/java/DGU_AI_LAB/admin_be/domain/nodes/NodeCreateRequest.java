package DGU_AI_LAB.admin_be.domain.nodes;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record NodeCreateRequest(
        @NotBlank String nodeName,
        @NotNull @Min(1) Integer numberGpu,
        @NotNull @Min(1) Integer memorySize,
        @NotNull @Min(1) Integer cpuCore,
        @NotNull Long resourceGroupId
) {
    public Node toEntity(ResourceGroup group) {
        return Node.builder()
                .nodeName(nodeName)
                .numberGpu(numberGpu)
                .memorySize(memorySize)
                .cpuCore(cpuCore)
                .resourceGroup(group)
                .build();
    }
}
