package DGU_AI_LAB.admin_be.domain.nodes;

import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resources")
public class NodeController {

    private final NodeService nodeService;

    @PostMapping("/nodes")
    public ResponseEntity<?> createNode(@RequestBody @Valid NodeCreateRequest request) {
        nodeService.createNode(request);
        return SuccessResponse.created(null);
    }
}