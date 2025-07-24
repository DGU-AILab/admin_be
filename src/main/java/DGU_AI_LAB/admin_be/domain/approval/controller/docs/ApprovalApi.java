package DGU_AI_LAB.admin_be.domain.approval.controller.docs;

import DGU_AI_LAB.admin_be.domain.approval.dto.request.ApprovalCreateRequest;
import DGU_AI_LAB.admin_be.domain.approval.dto.response.ApprovalResponseDTO;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "승인 관리", description = "승인 생성 및 조회 API")
public interface ApprovalApi {

    @Operation(summary = "승인 정보 조회", description = "사용자명(ubuntu username)으로 최신 승인 정보를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "승인 조회 성공",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> getApproval(
            @Parameter(description = "사용자명", example = "admin") @PathVariable String username
    );

    @Operation(summary = "승인 생성", description = "새로운 승인 정보를 생성합니다.")
    @ApiResponse(responseCode = "201", description = "승인 생성 성공",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> createApproval(
            @RequestBody @Valid ApprovalCreateRequest request
    );
}
