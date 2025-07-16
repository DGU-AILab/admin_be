package DGU_AI_LAB.admin_be.domain.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "node")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_group_id")
    private ResourceGroup resource_group;

    @Column(name = "node_name", nullable = false, unique = true)
    private String nodeName;

    @Column(name = "server_address")
    private String serverAddress;

    @Column(nullable = false)
    private String status = "active";
}
