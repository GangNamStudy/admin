package com.parking.admin.domain.common.exception;

import lombok.Getter;

@Getter
public enum BusinessErrorCode {

    /**
     * 비즈니스 로직 예외
     */
    // 결제상태 관련
    NULL_STATUS("상태 값은 null일 수 없습니다."),
    UNREGISTERED_PAYMENT_STATUS("등록되지 않은 결제 상태입니다 {status: %s}"),

    // 차량 출입 관련
    CANNOT_EXIT_UNPARKED_CAR("주차되지 않은 차량은 내보낼 수 없습니다."),
    EXIT_BEFORE_ENTRY("출차 시간이 입차시간보다 빠릅니다."),

    // 요금 관련
    ADDITIONAL_TIME_TOO_SHORT("추가 요금 시간은 1분 이상이어야 합니다."),

    // DurationTime 클래스
    NEGATIVE_DURATION("시간(분)은 음수일 수 없습니다."),

    // Money 클래스
    NEGATIVE_AMOUNT("금액은 음수일 수 없습니다.");

    private final String message;

    BusinessErrorCode(String message) {
        this.message = message;
    }

    /**
     * 메시지에 포맷 인자가 필요한 경우 사용 (예: %s 포함 메시지)
     */
    public String format(Object... args) {
        return String.format(this.message, args);
    }
}
