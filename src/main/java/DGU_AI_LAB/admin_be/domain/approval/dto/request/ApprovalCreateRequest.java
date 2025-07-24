package DGU_AI_LAB.admin_be.domain.approval.dto.request;

import DGU_AI_LAB.admin_be.domain.approval.entity.Approval;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApprovalCreateRequest(
        @NotNull Long userId,
        @NotNull Long resourceGroupId,
        @NotNull @Min(1) Integer volumeSize,
        @NotNull LocalDateTime validDate
) {
    public Approval toEntity(User user, ResourceGroup group) {
        return Approval.builder()
                .user(user)
                .resourceGroup(group)
                .volumeSize(volumeSize)
                .validDate(validDate)
                .approved(true)
                .build();
    }
}
