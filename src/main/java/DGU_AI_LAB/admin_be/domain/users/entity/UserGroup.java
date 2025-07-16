package DGU_AI_LAB.admin_be.domain.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_groups")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ubuntu_groupname", nullable = false)
    private String ubuntuGroupname;

    @OneToOne
    @JoinColumn(name = "ubuntu_gid", referencedColumnName = "id", nullable = false, unique = true)
    private UsedId usedGid;
}
