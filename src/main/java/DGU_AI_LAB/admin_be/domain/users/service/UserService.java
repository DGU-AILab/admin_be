package DGU_AI_LAB.admin_be.domain.users.service;

import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.domain.users.repository.UserRepository;
import DGU_AI_LAB.admin_be.error.ErrorCode;
import DGU_AI_LAB.admin_be.error.exception.EntityNotFoundException;
import DGU_AI_LAB.admin_be.error.exception.InternalServerException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import DGU_AI_LAB.admin_be.domain.users.repository.UsedIdRepository;
import DGU_AI_LAB.admin_be.domain.users.entity.UsedId;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UsedIdRepository usedIdRepository;
    private final UserRepository userRepository;

    /**
     * 유저 생성
     */
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * 단일 유저 조회
     */
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    /**
     * 전체 유저 조회
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * 유저 삭제
     */
    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * 유저 정보 수정
     */
    @Transactional
    public User updateUser(Long userId, User updatedUser) {
        User user = getUserById(userId);
        user.updateUserInfo(
                updatedUser.getUsername(),
                updatedUser.getPassword(),
                updatedUser.getIsActive()
        );
        return user;
    }

    /**
     * 사용하지 않은 UID/GID를 찾아 할당
     */
    @Transactional
    public Long allocateNextAvailableUidGid() {
        long base = 10_000L;

        for (long candidate = base; candidate < Integer.MAX_VALUE; candidate++) {
            if (!usedIdRepository.existsById(candidate)) {
                usedIdRepository.save(UsedId.builder().id(candidate).build());
                return candidate;
            }
        }

        throw new InternalServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
