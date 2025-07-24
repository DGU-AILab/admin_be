package DGU_AI_LAB.admin_be.domain.admins.entity;

import DGU_AI_LAB.admin_be.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Admin extends BaseTimeEntity {

    // TODO: ERD 수정하여 user와 분리할건지 확정 필요

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }
}