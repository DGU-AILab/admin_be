package DGU_AI_LAB.admin_be.domain.approval.service;

import DGU_AI_LAB.admin_be.domain.approval.dto.ApprovalCreateRequest;
import DGU_AI_LAB.admin_be.domain.approval.dto.ApprovalResponse;
import DGU_AI_LAB.admin_be.domain.approval.entity.Approval;
import DGU_AI_LAB.admin_be.domain.approval.repository.ApprovalRepository;
import DGU_AI_LAB.admin_be.domain.resourceGroups.repository.ResourceGroupRepository;
import DGU_AI_LAB.admin_be.domain.users.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApprovalService {

    private final ApprovalRepository approvalRepository;
    private final UserRepository userRepository;
    private final ResourceGroupRepository resourceGroupRepository;

    public ApprovalResponse getApprovalByUsername(String username) {
        Approval approval = approvalRepository
                .findFirstByUserUsernameAndApprovedIsTrueOrderByCreatedAtDesc(username)
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_APPROVAL_NOT_FOUND));

        var group = approval.getResourceGroup();

        return ApprovalResponse.builder()
                .username(approval.getUser().getUsername())
                .volumeSize(approval.getVolumeSize())
                .approvedAt(approval.getCreatedAt())
                .resourceGroup(
                        ApprovalResponse.ResourceGroupDTO.builder()
                                .id(group.getResourceGroupId())
                                .name(group.getResourceGroupName())
                                .groupType(group.getGroupType())
                                .groupNumber(group.getGroupNumber())
                                .build()
                )
                .build();
    }

    public void createApproval(ApprovalCreateRequest request) {
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        var group = resourceGroupRepository.findById(request.resourceGroupId())
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_GROUP_NOT_FOUND));

        approvalRepository.save(request.toEntity(user, group));
    }
}