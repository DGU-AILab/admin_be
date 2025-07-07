package DGU_AI_LAB.admin_be.global.common;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class SuccessResponse<T> {
    private int status;
    private String message;
    private T data;

    public static <T> ResponseEntity<SuccessResponse<?>> ok(T data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessResponse.of(SuccessCode.OK, data));
    }

    public static <T> ResponseEntity<SuccessResponse<?>> created(T data) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(SuccessResponse.of(SuccessCode.CREATED, data));
    }

    public static <T> SuccessResponse<?> of(SuccessCode successCode, T data) {
        return SuccessResponse.builder()
                .status(successCode.getHttpStatus().value())
                .message(successCode.getMessage())
                .data(data)
                .build();
    }

}
