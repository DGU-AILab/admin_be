package DGU_AI_LAB.admin_be.domain.admins.controller;

import DGU_AI_LAB.admin_be.domain.admins.controller.docs.AuthApi;
import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.dto.response.UserTokenResponseDTO;
import DGU_AI_LAB.admin_be.domain.admins.service.AdminLoginService;
import DGU_AI_LAB.admin_be.domain.admins.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AdminLoginService adminLoginService;
    private final AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<UserTokenResponseDTO> login(@RequestBody @Valid UserLoginRequestDTO request) {
        return ResponseEntity.ok(adminLoginService.login(request));
    }

    // 테스트용 회원가입
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserLoginRequestDTO request) {
        adminService.saveAdmin(request);
        return ResponseEntity.ok().build();
    }
}