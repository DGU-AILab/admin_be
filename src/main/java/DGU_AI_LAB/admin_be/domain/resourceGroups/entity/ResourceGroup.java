package DGU_AI_LAB.admin_be.domain.resourceGroups.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "resource_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class ResourceGroup {

    @Id @GeneratedValue
    private Long resourceGroupId;

    @Column(nullable = false, unique = true)
    private String resourceGroupName; // ex: 3090ti

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupType groupType; // LAB / FARM

    @Column(name = "group_number")
    private Integer groupNumber;

    @Column(columnDefinition = "TEXT")
    private String resourceGroupDescription;
}
