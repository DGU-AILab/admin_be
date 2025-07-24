package DGU_AI_LAB.admin_be.domain.resourceGroups.controller;

import DGU_AI_LAB.admin_be.domain.resourceGroups.controller.docs.ResourceGroupApi;
import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupCreateRequest;
import DGU_AI_LAB.admin_be.domain.resourceGroups.dto.ResourceGroupResponse;
import DGU_AI_LAB.admin_be.domain.resourceGroups.service.ResourceGroupService;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resources")
public class ResourceGroupController implements ResourceGroupApi {

    private final ResourceGroupService resourceGroupService;

    @PostMapping("/groups")
    public ResponseEntity<?> createResourceGroup(@RequestBody @Valid ResourceGroupCreateRequest request) {
        resourceGroupService.createResourceGroup(request);
        return SuccessResponse.created(null);
    }

    @GetMapping
    public ResponseEntity<?> getAllResourceGroups() {
        List<ResourceGroupResponse> response = resourceGroupService.getAllResourceGroups();
        return SuccessResponse.ok(response);
    }
}
