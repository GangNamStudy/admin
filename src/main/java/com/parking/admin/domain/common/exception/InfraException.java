package com.parking.admin.domain.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InfraException extends RuntimeException {
    private final ErrorCode errorCode;
}
