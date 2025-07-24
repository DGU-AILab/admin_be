package DGU_AI_LAB.admin_be.domain.resourceGroups.dto;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.GroupType;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Schema(description = "리소스 그룹 생성 요청 DTO")
@Builder
public record ResourceGroupCreateRequest(

        @Schema(description = "리소스 그룹 이름", example = "3090ti")
        @NotBlank String name,

        @Schema(description = "그룹 타입", example = "FARM/LAB")
        @NotNull GroupType groupType,

        @Schema(description = "기타 설명", example = "이 서버 특이사항은 ~")
        String description // optional
) {
    public ResourceGroup toEntity() {
        return ResourceGroup.builder()
                .resourceGroupName(name)
                .groupType(groupType)
                .resourceGroupDescription(description)
                .build();
    }
}
