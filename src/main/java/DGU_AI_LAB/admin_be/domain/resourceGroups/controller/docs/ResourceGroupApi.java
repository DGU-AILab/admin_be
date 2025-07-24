package DGU_AI_LAB.admin_be.domain.resourceGroups.controller.docs;

import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupCreateRequest;
import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupResponse;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "리소스 그룹", description = "GPU 서버 리소스 그룹(3090ti 등 서버 그룹 묶음)생성 및 조회 API")
public interface ResourceGroupApi {

    @Operation(summary = "리소스 그룹 생성", description = "GPU 서버 스펙별 리소스 그룹을 등록합니다.")
    @ApiResponse(responseCode = "201", description = "리소스 그룹 생성 성공",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> createResourceGroup(@RequestBody @Valid ResourceGroupCreateRequest request);

    @Operation(summary = "리소스 그룹 전체 조회", description = "등록된 모든 리소스 그룹 목록을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "리소스 그룹 목록 반환",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> getAllResourceGroups();
}
