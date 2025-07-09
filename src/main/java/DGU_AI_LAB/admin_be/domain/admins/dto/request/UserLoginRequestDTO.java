package DGU_AI_LAB.admin_be.domain.admins.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDTO(
        @NotBlank String name,
        @NotBlank String password
) {}

