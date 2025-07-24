package DGU_AI_LAB.admin_be.domain.requests.dto.response;

import DGU_AI_LAB.admin_be.domain.requests.entity.Answer;

public record AnswerResponseDTO(
        String question,
        String response
) {
    public static AnswerResponseDTO fromEntity(Answer answer) {
        return new AnswerResponseDTO(
                answer.getQuestion(),
                answer.getResponse()
        );
    }
}