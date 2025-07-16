package DGU_AI_LAB.admin_be.domain.users.entity;

import DGU_AI_LAB.admin_be.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "is_active", nullable = false)
    @Builder.Default
    private Boolean isActive = true;

    public void updateUserInfo(String username, String password, Boolean isActive) {
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    @ManyToOne
    @JoinColumn(name = "resource_group_id", nullable = false)
    private ResourceGroup resourceGroup;

    // 한 명의 유저가 여러 개의 키를 사용할 수 있음.
    // 여러 PC에서 접속하면 그만큼 키의 수가 늘어남.
    @OneToMany(mappedBy = "user")
    private List<UserKey> userKeys;


}