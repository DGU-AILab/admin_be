package DGU_AI_LAB.admin_be.error.exception;

import DGU_AI_LAB.admin_be.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException() {
        super(ErrorCode.ENTITY_NOT_FOUND);
    }
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}

