package DGU_AI_LAB.admin_be.domain.admins.repository;

import DGU_AI_LAB.admin_be.domain.admins.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByName(String name);
}

