package DGU_AI_LAB.admin_be.domain.nodes.dto;

import DGU_AI_LAB.admin_be.domain.nodes.entity.Node;
import lombok.Builder;

@Builder
public record NodeResponse(
        Long id,
        String nodeName,
        Integer numberGpu,
        Integer memorySize,
        Integer cpuCore,
        Long resourceGroupId
) {
    public static NodeResponse fromEntity(Node node) {
        return NodeResponse.builder()
                .id(node.getNodeId())
                .nodeName(node.getNodeName())
                .numberGpu(node.getNumberGpu())
                .memorySize(node.getMemorySize())
                .cpuCore(node.getCpuCore())
                .resourceGroupId(node.getResourceGroup().getResourceGroupId())
                .build();
    }
}