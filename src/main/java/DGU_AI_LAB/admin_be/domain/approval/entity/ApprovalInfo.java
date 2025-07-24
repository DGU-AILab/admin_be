package DGU_AI_LAB.admin_be.domain.approval.entity;

import DGU_AI_LAB.admin_be.domain.users.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "approveInfo")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ApprovalInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long approveInfoId;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "serverName", nullable = false)
    private String serverName;

    @Column(name = "validDate", nullable = false)
    private LocalDateTime validDate;

    @Column(name = "volumeSize", nullable = false)
    private Long volumeSize;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_group_id", nullable = false)
    private ResourceGroup resourceGroup;

    // uid, image_id
}