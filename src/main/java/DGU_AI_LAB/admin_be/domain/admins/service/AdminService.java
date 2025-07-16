package DGU_AI_LAB.admin_be.domain.admins.service;

import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.entity.Admin;
import DGU_AI_LAB.admin_be.domain.admins.repository.AdminRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Admin saveAdmin(UserLoginRequestDTO userLoginRequest) {
        adminRepository.findByName(userLoginRequest.name()).ifPresent(admin -> {
            throw new InvalidValueException(ErrorCode.DUPLICATE_NAME);
        });

        Admin admin = Admin.builder()
                .name(userLoginRequest.name())
                .password(passwordEncoder.encode(userLoginRequest.password()))
                .build();

        return adminRepository.save(admin);
    }
}
