package DGU_AI_LAB.admin_be.global.auth;

import java.util.List;

public class SecurityWhitelist {

    public static final String[] SPRING_WHITE_LIST = {
            "/", "/swagger/**", "/swagger-ui/**", "/v3/api-docs/**",
            "/api/auth/token/**", "/api/auth/login", "/api/auth/reissue",
            "/auth/callback/**", "/api/auth/register"
    };

    public static final List<String> EXACT_SKIP_PATHS = List.of(
            "/", "/swagger-ui", "/v3/api-docs", "/auth/callback", "/api/auth/login", "/api/auth/register"
    );

    public static final List<String> PATTERN_SKIP_PATHS = List.of(
            // 여기에 토큰 필요없는 경로 작성
    );
}

