package DGU_AI_LAB.admin_be.domain.users.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resource_group")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ResourceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_group_id")
    private Long resoureGroupId;

    @Column(name = "resource_group_name", nullable = false, unique = true)
    private String resourceGroupName;

    @Column(name = "resource_group_description", columnDefinition = "TEXT")
    private String resourceGroupDescription;
}
