package DGU_AI_LAB.admin_be.domain.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ubuntu_groupname", nullable = false)
    private String ubuntuGroupname;

    @OneToOne
    @JoinColumn(name = "ubuntu_gid", referencedColumnName = "id", nullable = false, unique = true)
    private UsedId usedGid;
}
