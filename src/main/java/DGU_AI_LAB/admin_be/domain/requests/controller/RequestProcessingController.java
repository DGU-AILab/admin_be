package DGU_AI_LAB.admin_be.domain.requests.controller;

import DGU_AI_LAB.admin_be.domain.approval.entity.ApprovalInfo;
import DGU_AI_LAB.admin_be.domain.requests.dto.request.RequestApproveDTO;
import DGU_AI_LAB.admin_be.domain.requests.service.RequestProcessingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
@RequiredArgsConstructor
public class RequestProcessingController {

    private final RequestProcessingService requestProcessingService;

    @PostMapping("/approve")
    public ResponseEntity<?> approve(@Valid @RequestBody RequestApproveDTO request) {
        requestProcessingService.approveRequest(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/approve/{id}")
    public ResponseEntity<ApprovalInfo> getApprovalInfo(@PathVariable Long id) {
        ApprovalInfo approvalInfo = requestProcessingService.getApprovalInfoById(id);
        return ResponseEntity.ok(approvalInfo);
    }
}