package DGU_AI_LAB.admin_be.domain.admins.service;

import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.admins.entity.Admin;
import DGU_AI_LAB.admin_be.domain.admins.repository.AdminRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.UnauthorizedException;
import DGU_AI_LAB.admin_be.global.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;

    private final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7;

    public UserTokenResponseDTO login(UserLoginRequestDTO request) {
        Admin admin = adminRepository.findByName(request.name())
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.INVALID_LOGIN_INFO));

        if (!passwordEncoder.matches(request.password(), admin.getPassword())) {
            throw new UnauthorizedException(ErrorCode.INVALID_LOGIN_INFO);
        }

        String accessToken = jwtProvider.getIssueToken(admin.getAdminId(), true);
        String refreshToken = jwtProvider.getIssueToken(admin.getAdminId(), false);

        redisTemplate.opsForValue().set(
                "RT:" + admin.getAdminId(), refreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS
        );

        return UserTokenResponseDTO.of(accessToken, refreshToken);
    }
}