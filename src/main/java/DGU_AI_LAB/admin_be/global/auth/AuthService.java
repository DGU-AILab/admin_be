package DGU_AI_LAB.admin_be.global.auth;

import DGU_AI_LAB.admin_be.domain.admins.entity.User;
import DGU_AI_LAB.admin_be.domain.admins.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    /**
     * JWT 인증 필터에서 userId 기반으로 User 엔티티를 조회할 때 사용
     */
    public User loadUserEntityById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_FOUND));
    }
}
