package DGU_AI_LAB.admin_be.error.exception;

import DGU_AI_LAB.admin_be.error.ErrorCode;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}