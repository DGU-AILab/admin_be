package DGU_AI_LAB.admin_be.domain.approval.entity;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "approval")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Approval extends BaseTimeEntity{

    @Id
    @GeneratedValue
    private Long approvalId;

    @Column(nullable = false)
    private Integer volumeSize; // GB

    @Column(nullable = false)
    private Boolean approved; // true면 승인됨

    @Column(name = "validDate")
    private LocalDateTime validDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_group_id")
    private ResourceGroup resourceGroup;


    /* TODO: 이 부분 필요한지 다시 논의해야 합니다!
    @Column(nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
     */

}

