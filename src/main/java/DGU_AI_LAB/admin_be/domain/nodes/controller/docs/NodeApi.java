package DGU_AI_LAB.admin_be.domain.nodes.controller.docs;

import DGU_AI_LAB.admin_be.domain.nodes.dto.request.NodeCreateRequest;
import DGU_AI_LAB.admin_be.domain.nodes.dto.response.NodeResponse;
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

@Tag(name = "노드 관리", description = "GPU/서버 노드 등록 및 조회 API")
public interface NodeApi {

    @Operation(summary = "노드 등록", description = "GPU 노드를 새로 등록합니다.")
    @ApiResponse(responseCode = "201", description = "노드 생성 성공",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> createNode(@RequestBody @Valid NodeCreateRequest request);

    @Operation(summary = "전체 노드 조회", description = "등록된 모든 노드를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "노드 목록 반환",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> getAllNodes();

    @Operation(summary = "단일 노드 조회", description = "ID로 특정 노드를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "노드 반환",
            content = @Content(schema = @Schema(implementation = SuccessResponse.class)))
    ResponseEntity<?> getNodeById(@Parameter(description = "노드 ID", example = "1") @PathVariable Long id);
}
