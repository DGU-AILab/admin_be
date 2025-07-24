package DGU_AI_LAB.admin_be.domain.nodes.entity;

import DGU_AI_LAB.admin_be.domain.resourceGroups.entity.ResourceGroup;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "node")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Node {

    @Id @GeneratedValue
    private Long nodeId;

    @Column(nullable = false, unique = true)
    private String nodeName; // Lab 1, Farm 2 ...

    @Column(nullable = false)
    private Integer numberGpu; // GPU 개수

    @Column(nullable = false)
    private Integer memorySize; // GB 단위

    @Column(nullable = false)
    private Integer cpuCore; // CPU 코어 개수

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_group_id")
    private ResourceGroup resourceGroup;

}

