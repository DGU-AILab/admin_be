package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import DGU_AI_LAB.admin_be.domain.requests.entity.Answer;

public record RequestAnswerDTO(
        String question,
        String response
) {
    public static RequestAnswerDTO fromEntity(Answer answer) {
        return new RequestAnswerDTO(
                answer.getQuestion(),
                answer.getResponse()
        );
    }
}