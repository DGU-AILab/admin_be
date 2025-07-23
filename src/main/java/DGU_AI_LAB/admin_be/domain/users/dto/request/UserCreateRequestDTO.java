package DGU_AI_LAB.admin_be.domain.users.dto.request;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateRequestDTO(
        @NotBlank String username,
        @NotBlank String password,
        @NotNull Boolean isActive,
        @NotNull Long resourceGroupId
) {
    public User toEntity(ResourceGroup resourceGroup) {
        return User.builder()
                .username(username)
                .password(password)
                .isActive(isActive)
                .resourceGroup(resourceGroup)
                .build();
    }
}
