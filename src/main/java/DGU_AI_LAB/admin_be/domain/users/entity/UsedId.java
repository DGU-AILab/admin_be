package DGU_AI_LAB.admin_be.domain.users.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_ids")
@Getter @Setter
@NoArgsConstructor
public class UsedId {
    @Id
    private Integer id;
}
