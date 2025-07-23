package DGU_AI_LAB.admin_be.domain.approval.dto;

import DGU_AI_LAB.admin_be.domain.approval.entity.Approval;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ApprovalCreateRequest(
        @NotNull Long userId,
        @NotNull Long resourceGroupId,
        @NotNull @Min(1) Integer volumeSize
) {
    public Approval toEntity(User user, ResourceGroup group) {
        return Approval.builder()
                .user(user)
                .resourceGroup(group)
                .volumeSize(volumeSize)
                .approved(true)
                .build();
    }
}
