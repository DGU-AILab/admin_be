package DGU_AI_LAB.admin_be.global.auth;

import DGU_AI_LAB.admin_be.domain.admins.entity.Admin;
import DGU_AI_LAB.admin_be.domain.admins.repository.AdminRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AdminRepository adminRepository;

    /**
     * JWT 인증 필터에서 adminId 기반으로 Admin 엔티티를 조회할 때 사용
     */
    public Admin loadAdminEntityById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_FOUND));
    }
}
