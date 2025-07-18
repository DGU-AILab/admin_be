package DGU_AI_LAB.admin_be.domain.requests.service;

import DGU_AI_LAB.admin_be.domain.requests.dto.request.RequestAnswerDTO;
import DGU_AI_LAB.admin_be.domain.requests.dto.request.RequestDTO;
import DGU_AI_LAB.admin_be.domain.requests.entity.Answer;
import DGU_AI_LAB.admin_be.domain.requests.entity.Request;
import DGU_AI_LAB.admin_be.domain.requests.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestService {
    private final RequestRepository requestRepository;

    @Transactional
    public Request saveRequest(RequestDTO UseRequest) {
        Request request = Request.builder()
                .serverName(UseRequest.getServerName())
                .build();

        List<Answer> answers = UseRequest.getAnswers().stream()
                .map(dto -> Answer.builder()
                        .question(dto.getQuestion())
                        .response(dto.getResponse())
                        .request(request)
                        .build())
                .toList();

        request.getAnswers().addAll(answers);

        return requestRepository.save(request);
    }

    // 전체 사용 신청 목록 조회
    public List<RequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(request -> new RequestDTO(
                        request.getRequestId(),
                        request.getServerName(),
                        request.getAnswers().stream()
                                .map(answer -> new RequestAnswerDTO(answer.getQuestion(), answer.getResponse()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    // 개별 사용 신청 조회
    public RequestDTO getRequestById(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id " + id));

        return new RequestDTO(
                request.getRequestId(),
                request.getServerName(),
                request.getAnswers().stream()
                        .map(answer -> new RequestAnswerDTO(answer.getQuestion(), answer.getResponse()))
                        .collect(Collectors.toList())
        );
    }
}