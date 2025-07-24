package DGU_AI_LAB.admin_be.domain.approval.dto.response;

import DGU_AI_LAB.admin_be.domain.approval.entity.ApprovalInfo;

import java.time.LocalDateTime;

public record ApprovalResponseDTO(
        Long approveInfoId,
        String username,
        String serverName,
        LocalDateTime validDate,
        Long volumeSize
) {
    public static ApprovalResponseDTO fromEntity(ApprovalInfo approvalInfo) {
        return new ApprovalResponseDTO(
                approvalInfo.getApproveInfoId(),
                approvalInfo.getUsername(),
                approvalInfo.getServerName(),
                approvalInfo.getValidDate(),
                approvalInfo.getVolumeSize()
        );
    }
}