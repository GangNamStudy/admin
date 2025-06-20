package com.parking.admin.adapter.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum InfraErrorCode {
    FAILURE_TO_CREATE_POLICY("policy를 불러올 수 없습니다."),
    FAILURE_TO_ENTRY_CAR("차량을 입차할 수 없습니다"),
    FAILURE_TO_EXIT_CAR("차량을 출차할 수 없습니다"),
    FAILURE_TO_READ_CAR_INFO("차량 정보를 불러올 수 없습니다"),
    FAILURE_TO_READ_PAYMENT_HISTORY("결제 정보를 불러올 수 없습니다");

    private final String message;
}
