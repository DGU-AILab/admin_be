package DGU_AI_LAB.admin_be.domain.user.controller;

import DGU_AI_LAB.admin_be.domain.user.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.user.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.user.service.UserLoginService;
import DGU_AI_LAB.admin_be.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserLoginService userLoginService;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<UserTokenResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO request) {
        return ResponseEntity.ok(userLoginService.login(request));
    }

    // 테스트용 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserLoginRequestDTO request) {
        userService.saveUser(request);
        return ResponseEntity.ok().build();
    }
}
