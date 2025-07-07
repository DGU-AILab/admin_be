package DGU_AI_LAB.admin_be.error.exception;

import DGU_AI_LAB.admin_be.error.ErrorCode;

public class InternalServerException extends BusinessException {
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }
}