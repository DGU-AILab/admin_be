package DGU_AI_LAB.admin_be.domain.admins.service;

import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserTokenRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.admins.repository.AdminRepository;
import DGU_AI_LAB.admin_be.error.exception.UnauthorizedException;
import DGU_AI_LAB.admin_be.global.auth.jwt.JwtProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static DGU_AI_LAB.admin_be.error.ErrorCode.JSON_PARSING_FAILED;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenService {
    private final JwtProvider jwtProvider;
    private final RedisTemplate<String, String> redisTemplate;
    private final AdminRepository adminRepository;

    @Value("${jwt.refresh-token-expire-time}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    public String issueNewAccessToken(Long adminId) {
        return jwtProvider.getIssueToken(adminId, true);
    }

    public String issueNewRefreshToken(Long adminId) {
        return jwtProvider.getIssueToken(adminId, false);
    }

    public UserTokenResponseDTO issueTempToken(Long adminId) {
        String accessToken = issueNewAccessToken(adminId);
        String refreshToken = issueNewRefreshToken(adminId);
        return UserTokenResponseDTO.of(accessToken, refreshToken);
    }

    public UserTokenResponseDTO issueToken(Long adminId) {

        String accessToken = jwtProvider.getIssueToken(adminId, true);

        String redisKey = "RT:" + adminId;
        String storedRefreshToken = redisTemplate.opsForValue().get(redisKey);

        if (storedRefreshToken == null) {
            storedRefreshToken = issueNewRefreshToken(adminId);
            redisTemplate.opsForValue().set(redisKey, storedRefreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
        }

        return UserTokenResponseDTO.of(accessToken, storedRefreshToken);
    }

    public UserTokenResponseDTO reissue(UserTokenRequestDTO userTokenRequest){
        Long adminId;
        try {
            adminId = Long.valueOf(jwtProvider.decodeJwtPayloadSubject(userTokenRequest.accessToken()));
        } catch (JsonProcessingException e) {
            throw new UnauthorizedException(JSON_PARSING_FAILED);
        }

        String refreshToken = userTokenRequest.refreshToken();
        String redisKey = "RT:" + adminId;

        jwtProvider.validateRefreshToken(refreshToken);

        String storedRefreshToken = redisTemplate.opsForValue().get(redisKey);
        jwtProvider.equalsRefreshToken(refreshToken, storedRefreshToken);

        String newAccessToken = issueNewAccessToken(adminId);
        String newRefreshToken = issueNewRefreshToken(adminId);

        redisTemplate.opsForValue().set(redisKey, newRefreshToken, REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);

        return UserTokenResponseDTO.of(newAccessToken, newRefreshToken);
    }

    public void logout(Long adminId) {
        String redisKey = "RT:" + adminId;
        redisTemplate.delete(redisKey);
    }
}
