package DGU_AI_LAB.admin_be.domain.admins.dto.response;

public record UserTokenResponseDTO(
        String accessToken,
        String refreshToken
) {
    public static UserTokenResponseDTO of(String accessToken, String refreshToken) {
        return new UserTokenResponseDTO(accessToken, refreshToken);
    }
}
