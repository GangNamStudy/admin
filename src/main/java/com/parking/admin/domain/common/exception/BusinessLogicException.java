package com.parking.admin.domain.common.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends RuntimeException {
    private final BusinessErrorCode errorCode;

    public BusinessLogicException(BusinessErrorCode businessErrorCode) {
        super(businessErrorCode.getMessage());
        this.errorCode = businessErrorCode;
    }

    public BusinessLogicException(BusinessErrorCode businessErrorCode, String message) {
        super(businessErrorCode.format(message));
        this.errorCode = businessErrorCode;
    }
}
