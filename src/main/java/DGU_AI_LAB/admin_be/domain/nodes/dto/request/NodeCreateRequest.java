package DGU_AI_LAB.admin_be.domain.nodes.dto.request;

import DGU_AI_LAB.admin_be.domain.nodes.entity.Node;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Schema(description = "노드 등록 요청 DTO")
@Builder
public record NodeCreateRequest(
        @Schema(description = "노드(서버) 이름", example = "farm 1")
        @NotBlank String nodeName,

        @Schema(description = "GPU 개수", example = "4")
        @NotNull @Min(1) Integer numberGpu,

        @Schema(description = "메모리(GB)", example = "128")
        @NotNull @Min(1) Integer memorySize,

        @Schema(description = "CPU 코어 수", example = "32")
        @NotNull @Min(1) Integer cpuCore,

        @Schema(description = "자원그룹(3090ti 그룹..등)의 ID", example = "1")
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
