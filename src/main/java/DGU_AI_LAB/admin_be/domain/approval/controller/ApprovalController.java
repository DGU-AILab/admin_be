package DGU_AI_LAB.admin_be.domain.approval.controller;

import DGU_AI_LAB.admin_be.domain.approval.dto.ApprovalCreateRequest;
import DGU_AI_LAB.admin_be.domain.approval.dto.ApprovalResponse;
import DGU_AI_LAB.admin_be.domain.approval.service.ApprovalService;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/approvals")
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/{username}")
    public ResponseEntity<?> getApproval(@PathVariable String username) {
        ApprovalResponse response = approvalService.getApprovalByUsername(username);
        return SuccessResponse.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createApproval(@RequestBody @Valid ApprovalCreateRequest request) {
        approvalService.createApproval(request);
        return SuccessResponse.created(null);
    }
}