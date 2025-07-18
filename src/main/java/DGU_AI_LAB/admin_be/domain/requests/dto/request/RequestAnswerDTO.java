package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestAnswerDTO {
    private String question;
    private String response;
}