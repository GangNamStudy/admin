package com.parking.admin.domain.common.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessLogicException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessLogicException(ErrorCode errorCode, String message) {
        super(errorCode.format(message));
        this.errorCode = errorCode;
    }
}
