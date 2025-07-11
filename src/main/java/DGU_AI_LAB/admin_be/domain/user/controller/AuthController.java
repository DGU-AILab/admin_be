package DGU_AI_LAB.admin_be.domain.user.controller;

import DGU_AI_LAB.admin_be.domain.user.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.user.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.user.service.UserLoginService;
import DGU_AI_LAB.admin_be.domain.user.service.UserService;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi{

    private final UserLoginService userLoginService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<?>> login(
            @RequestBody @Valid UserLoginRequestDTO request
    ) {
        return SuccessResponse.ok(userLoginService.login(request));
    }

    // 테스트용 회원가입
    @PostMapping("/register")
    public ResponseEntity<SuccessResponse<?>> register(
            @RequestBody @Valid UserLoginRequestDTO request
    ) {
        return SuccessResponse.ok(userService.saveUser(request));
    }
}
