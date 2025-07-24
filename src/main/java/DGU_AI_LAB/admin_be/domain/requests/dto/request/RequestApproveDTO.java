package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import DGU_AI_LAB.admin_be.domain.users.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.domain.approval.entity.ApprovalInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import DGU_AI_LAB.admin_be.domain.users.entity.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.time.LocalDateTime;

public record RequestApproveDTO(
        @NotBlank String name,
        @NotBlank String email,
        @NotNull Long resource_group_id,
        @NotNull String username,
        @NotNull String serverName,
        @NotNull LocalDateTime validDate,
        @NotNull Long volumeSize
) {
    public static final Role DEFAULT_ROLE = Role.USER;

    public User toUserEntity(PasswordEncoder passwordEncoder, String defaultPassword) {
        return User.builder()
                .name(name)
                .email(email)
                .webId(username)
                .password(passwordEncoder.encode(defaultPassword))
                .role(DEFAULT_ROLE)
                .build();
    }

    public ApprovalInfo toApprovalInfoEntity(PasswordEncoder passwordEncoder, String defaultPassword, ResourceGroup resourceGroup, User user) {
        return ApprovalInfo.builder()
                .user(user)
                .username(username)
                .password(passwordEncoder.encode(defaultPassword))
                .serverName(serverName)
                .resourceGroup(resourceGroup)
                .validDate(validDate)
                .volumeSize(volumeSize)
                // uid, image_id
                .build();
    }
}
