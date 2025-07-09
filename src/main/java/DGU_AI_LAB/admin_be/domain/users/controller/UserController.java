package DGU_AI_LAB.admin_be.domain.users.controller;

import DGU_AI_LAB.admin_be.domain.users.entity.User;
import DGU_AI_LAB.admin_be.domain.users.service.UserService;
import DGU_AI_LAB.admin_be.global.common.SuccessResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createUser(@RequestBody User user) {
        return SuccessResponse.ok(userService.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> getUser(@PathVariable Long id) {
        return SuccessResponse.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllUsers() {
        return SuccessResponse.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> updateUser(@PathVariable Long id, @RequestBody User user) {
        return SuccessResponse.ok(userService.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse<?>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return SuccessResponse.ok(null);
    }
}
