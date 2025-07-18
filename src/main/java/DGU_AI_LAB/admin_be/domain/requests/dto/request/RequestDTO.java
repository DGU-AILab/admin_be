package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {
    private Long requestId;
    private String serverName;
    private List<RequestAnswerDTO> answers;
}