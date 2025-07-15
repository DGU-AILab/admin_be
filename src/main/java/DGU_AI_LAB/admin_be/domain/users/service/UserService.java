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

    // Create
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read - 단일 건
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND));
    }

    // Read - 전체
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setPasswordHash(updatedUser.getPasswordHash());
        user.setIsActive(updatedUser.getIsActive());
        return userRepository.save(user);
    }

    // Delete
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 사용하지 않는 다음 Uid, Gid 를 가져오기 위함
    @Transactional
    public Long allocateNextAvailableUidGid() {
        int base = 10000;

        for (long candidate = base; candidate < Integer.MAX_VALUE; candidate++) {
            if (!usedIdRepository.existsById(candidate)) {
                UsedId newId = new UsedId();
                newId.setId(candidate); // manually set ID
                usedIdRepository.save(newId);
                return candidate;
            }
        }
        throw new InternalServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
