package DGU_AI_LAB.admin_be.domain.requests.service;

import DGU_AI_LAB.admin_be.domain.requests.dto.request.RequestApproveDTO;
import DGU_AI_LAB.admin_be.domain.approval.dto.response.ApprovalResponseDTO;
import DGU_AI_LAB.admin_be.domain.requests.repository.RequestRepository;
import DGU_AI_LAB.admin_be.domain.users.repository.ResourceGroupRepository;
import DGU_AI_LAB.admin_be.domain.users.repository.UserRepository;
import DGU_AI_LAB.admin_be.domain.approval.repository.ApprovalInfoRepository;
import DGU_AI_LAB.admin_be.domain.approval.entity.ApprovalInfo;
import DGU_AI_LAB.admin_be.domain.users.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.error.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestProcessingService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final ApprovalInfoRepository approvalInfoRepository;
    private final ResourceGroupRepository resourceGroupRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${app.default-password}")
    private String defaultPassword;

    @Transactional
    public ApprovalResponseDTO approveRequest(RequestApproveDTO request) {
        // 자원그룹 조회
        ResourceGroup resourceGroup = resourceGroupRepository.findById(request.resource_group_id())
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));

        // 사용자 및 컨테이너 정보 저장
        User user = userRepository.save(request.toUserEntity(passwordEncoder, defaultPassword));
        ApprovalInfo approveInfo = request.toApprovalInfoEntity(passwordEncoder, defaultPassword, resourceGroup, user);
        approvalInfoRepository.save(approveInfo);

        return ApprovalResponseDTO.fromEntity(approveInfo);
    }

    public ApprovalInfo getApprovalInfoById(Long id) {
        return approvalInfoRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
