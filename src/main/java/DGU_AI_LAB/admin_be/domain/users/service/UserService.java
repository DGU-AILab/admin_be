package DGU_AI_LAB.admin_be.domain.users.service;

import DGU_AI_LAB.admin_be.domain.users.dto.request.UserCreateRequestDTO;
import DGU_AI_LAB.admin_be.domain.users.dto.response.UserResponseDTO;
import DGU_AI_LAB.admin_be.domain.users.dto.request.UserUpdateRequestDTO;
import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.domain.resourceGroups.repository.ResourceGroupRepository;
import DGU_AI_LAB.admin_be.domain.users.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.EntityNotFoundException;
import DGU_AI_LAB.admin_be.error.exception.InternalServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import DGU_AI_LAB.admin_be.domain.users.repository.UsedIdRepository;
import DGU_AI_LAB.admin_be.domain.users.entity.UsedId;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UsedIdRepository usedIdRepository;
    private final UserRepository userRepository;
    private final ResourceGroupRepository resourceGroupRepository;


    /**
     * 유저 생성
     */
    @Transactional
    public UserResponseDTO createUser(UserCreateRequestDTO request) {
        log.info("[createUser] name={}", request.name());

        /* ResourceGroup resourceGroup = resourceGroupRepository.findById(request.resourceGroupId())
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));*/

        User user = request.toEntity();
        User saved = userRepository.save(user);

        log.info("[createUser] user created with userId={}", saved.getUserId());
        return UserResponseDTO.fromEntity(saved);
    }


    /**
     * 단일 유저 조회
     */
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long userId) {
        log.debug("[getUserById] userId={}", userId);
        return UserResponseDTO.fromEntity(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND)));
    }

    /**
     * 전체 유저 조회
     */
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        log.debug("[getAllUsers] 전체 유저 조회 시작");
        return userRepository.findAll().stream()
                .map(UserResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * 유저 삭제
     */
    @Transactional
    public void deleteUser(Long userId) {
        log.warn("[deleteUser] userId={} 삭제 시도", userId);
        if (!userRepository.existsById(userId)) {
            log.error("[deleteUser] userId={} 존재하지 않음", userId);
            throw new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND);
        }
        userRepository.deleteById(userId);
        log.info("[deleteUser] userId={} 삭제 완료", userId);
    }

    /**
     * 유저 정보 수정
     */
    @Transactional
    public UserResponseDTO updateUser(Long userId, UserUpdateRequestDTO request) {
        log.info("[updateUser] userId={} 정보 수정 시작", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));

        user.updateUserInfo(
                request.password(),
                request.isActive()
        );

        log.info("[updateUser] userId={} 정보 수정 완료", userId);
        return UserResponseDTO.fromEntity(user);
    }

    /**
     * 사용하지 않은 UID/GID를 찾아 할당
     */
    @Transactional
    public Long allocateNextAvailableUidGid() {
        long base = 10_000L;
        log.info("[allocateNextAvailableUidGid] UID/GID 할당 시작");

        for (long candidate = base; candidate < Integer.MAX_VALUE; candidate++) {
            if (!usedIdRepository.existsById(candidate)) {
                usedIdRepository.save(UsedId.builder().id(candidate).build());
                log.info("[allocateNextAvailableUidGid] 할당된 UID/GID: {}", candidate);
                return candidate;
            }
        }

        log.error("[allocateNextAvailableUidGid] 할당 실패 - UID/GID 한도 초과");
        throw new InternalServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
