package DGU_AI_LAB.admin_be.domain.nodes.controller;

import DGU_AI_LAB.admin_be.domain.nodes.dto.NodeResponse;
import DGU_AI_LAB.admin_be.domain.nodes.service.NodeService;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/nodes")
    public ResponseEntity<?> getAllNodes() {
        List<NodeResponse> response = nodeService.getAllNodes();
        return SuccessResponse.ok(response);
    }

    @GetMapping("/nodes/{id}")
    public ResponseEntity<?> getNodeById(@PathVariable Long id) {
        NodeResponse response = nodeService.getNodeById(id);
        return SuccessResponse.ok(response);
    }

    @GetMapping("/nodes/available")
    public ResponseEntity<?> getAvailableNodes() {
        List<NodeResponse> response = nodeService.getAvailableNodes();
        return SuccessResponse.ok(response);
    }
}