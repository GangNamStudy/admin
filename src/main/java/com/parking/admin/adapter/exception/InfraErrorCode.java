package com.parking.admin.adapter.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InfraErrorCode {
    FAILURE_TO_CREATE_POLICY("policy를 불러올 수 없습니다.");

    private final String message;
}
