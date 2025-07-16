package DGU_AI_LAB.admin_be.domain.admins.repository;

import DGU_AI_LAB.admin_be.domain.admins.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByName(String name);
}