package com.parking.admin.adapter.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InfraException extends RuntimeException {
    private final InfraErrorCode errorCode;
}
