package DGU_AI_LAB.admin_be.domain.requests.dto.request;

import java.util.List;
import java.util.stream.Collectors;
import DGU_AI_LAB.admin_be.domain.requests.entity.Request;
import DGU_AI_LAB.admin_be.domain.requests.entity.Answer;

public record SaveRequestDTO(
        Long requestId,
        String serverName,
        List<RequestAnswerDTO> answers
) {
    public static SaveRequestDTO fromEntity(Request request) {
        List<RequestAnswerDTO> answerDTOs = request.getAnswers().stream()
                .map(RequestAnswerDTO::fromEntity)
                .collect(Collectors.toList());

        return new SaveRequestDTO(
                request.getRequestId(),
                request.getServerName(),
                answerDTOs
        );
    }

    public Request toEntity() {
        Request request = Request.builder()
                .serverName(this.serverName)
                .build();

        List<Answer> answerEntities = this.answers.stream()
                .map(answerDTO -> answerDTO.toEntity(request))
                .toList();

        request.getAnswers().addAll(answerEntities);
        return request;
    }
}