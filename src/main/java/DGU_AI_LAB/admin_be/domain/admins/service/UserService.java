package DGU_AI_LAB.admin_be.domain.admins.service;


import DGU_AI_LAB.admin_be.domain.admins.dto.request.UserLoginRequestDTO;
import DGU_AI_LAB.admin_be.domain.admins.entity.User;
import DGU_AI_LAB.admin_be.domain.admins.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.InvalidValueException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 사용자 정보 저장
    @Transactional
    public User saveUser(UserLoginRequestDTO userLoginRequest) {
        userRepository.findByName(userLoginRequest.name()).ifPresent(user -> {
            throw new InvalidValueException(ErrorCode.DUPLICATE_NAME);
        });

        User user = User.builder()
                .name(userLoginRequest.name())
                .password(passwordEncoder.encode(userLoginRequest.password()))
                .build();

        return userRepository.save(user);
    }
}