package DGU_AI_LAB.admin_be.domain.admins.service;

import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.admins.entity.User;
import DGU_AI_LAB.admin_be.domain.admins.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.UnauthorizedException;
import DGU_AI_LAB.admin_be.global.auth.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

@Service
@RequiredArgsConstructor
public class UserLoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;

    private final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 60 * 24 * 7; // 7일

    public UserTokenResponseDTO login(UserLoginRequestDTO request) {
        User user = userRepository.findByName(request.name())
                .orElseThrow(() -> new UnauthorizedException(ErrorCode.USER_NOT_FOUND));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UnauthorizedException(ErrorCode.INVALID_LOGIN_INFO);
        }

        String accessToken = jwtProvider.getIssueToken(user.getUserId(), true);
        String refreshToken = jwtProvider.getIssueToken(user.getUserId(), false);

        redisTemplate.opsForValue().set(
                "RT:" + user.getUserId(), refreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS
        );

        return UserTokenResponseDTO.of(accessToken, refreshToken);
    }
}
