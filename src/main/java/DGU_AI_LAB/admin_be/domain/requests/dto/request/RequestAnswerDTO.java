package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import DGU_AI_LAB.admin_be.domain.requests.entity.Answer;
import DGU_AI_LAB.admin_be.domain.requests.entity.Request;
import jakarta.validation.constraints.NotBlank;

public record RequestAnswerDTO(
        @NotBlank String question,
        String response
) {
    public static RequestAnswerDTO fromEntity(Answer answer) {
        return new RequestAnswerDTO(
                answer.getQuestion(),
                answer.getResponse()
        );
    }

    public Answer toEntity(Request request) {
        return Answer.builder()
                .question(this.question)
                .response(this.response)
                .request(request)
                .build();
    }
}