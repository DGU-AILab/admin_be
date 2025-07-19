package DGU_AI_LAB.admin_be.domain.requests.service;

import DGU_AI_LAB.admin_be.domain.requests.dto.request.SaveRequestDTO;
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
    public SaveRequestDTO saveRequest(SaveRequestDTO UseRequest) {
        Request saved = requestRepository.save(UseRequest.toEntity());
        return SaveRequestDTO.fromEntity(saved);
    }

    public List<SaveRequestDTO> getAllRequests() {
        return requestRepository.findAll().stream()
                .map(SaveRequestDTO::fromEntity)
                .toList();
    }

    public SaveRequestDTO getRequestById(Long id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found with id " + id));
        return SaveRequestDTO.fromEntity(request);
    }
}