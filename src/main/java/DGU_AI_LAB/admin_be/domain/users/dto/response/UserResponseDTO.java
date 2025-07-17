package DGU_AI_LAB.admin_be.domain.users.dto.response;

import DGU_AI_LAB.admin_be.domain.users.entity.User;

public record UserResponseDTO(
        Long userId,
        String username,
        Boolean isActive,
        Long resourceGroupId
) {
    public static UserResponseDTO fromEntity(User user) {
        return new UserResponseDTO(
                user.getUserId(),
                user.getUsername(),
                user.getIsActive(),
                user.getResourceGroup().getResourceGroupId()
        );
    }
}
