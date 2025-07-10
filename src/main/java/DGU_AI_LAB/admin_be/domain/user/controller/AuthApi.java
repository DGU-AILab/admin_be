package DGU_AI_LAB.admin_be.domain.user.controller;

import DGU_AI_LAB.admin_be.domain.user.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.user.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "0. Auth", description = "관리자 인증 API")
public interface AuthApi {

    @Operation(
            summary = "로그인 (토큰 발급)",
            description = "username과 password를 입력받아 JWT 토큰을 발급합니다.",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserLoginRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "로그인 성공, JWT 토큰 반환",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "401", description = "잘못된 사용자 정보")
            }
    )
    ResponseEntity<SuccessResponse<?>> login(UserLoginRequestDTO request);

    @Operation(
            summary = "회원가입 (테스트용)",
            description = "username과 password를 입력받아 테스트용 계정을 생성합니다. 테스트용이며, 이후 수정될 예정입니다. ",
            requestBody = @RequestBody(
                    required = true,
                    content = @Content(schema = @Schema(implementation = UserLoginRequestDTO.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "회원가입 성공",
                            content = @Content(schema = @Schema(implementation = SuccessResponse.class))),
                    @ApiResponse(responseCode = "400", description = "유효하지 않은 요청")
            }
    )
    ResponseEntity<SuccessResponse<?>> register(UserLoginRequestDTO request);
}
