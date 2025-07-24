package DGU_AI_LAB.admin_be.domain.admins.controller.docs;

import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@Tag(name = "인증", description = "관리자 로그인 및 회원가입 API")
public interface AuthApi {

    @Operation(summary = "관리자 로그인", description = "아이디/비밀번호로 로그인하여 액세스 토큰을 발급받습니다.")
    @ApiResponse(
            responseCode = "200",
            description = "로그인 성공",
            content = @Content(schema = @Schema(implementation = UserTokenResponseDTO.class))
    )
    ResponseEntity<UserTokenResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO request);

    @Operation(summary = "테스트용 관리자 회원가입", description = "테스트용으로 관리자 계정을 등록합니다. (실서비스에서는 사용하지 않습니다)")
    @ApiResponse(
            responseCode = "200",
            description = "회원가입 성공",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class))
    )
    ResponseEntity<?> register(@RequestBody @Valid UserLoginRequestDTO request);
}
