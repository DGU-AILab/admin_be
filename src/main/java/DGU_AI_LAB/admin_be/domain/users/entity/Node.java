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
    @Column(name = "node_id")
    private Long nodeId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_group_id")
    private ResourceGroup resourceGroup;

    @Column(name = "node_name", nullable = false, unique = true)
    private String nodeName;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NodeStatus status = NodeStatus.ACTIVE;

}
