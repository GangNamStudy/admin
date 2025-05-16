package com.parking.admin.domain.vo;

public enum PaymentStatus {
    PENDING,
    AUTHORIZED,
    SUCCESS,
    FAILED,
    PARTIALLY_CANCELED,
    CANCELED;

    public static PaymentStatus of(String status) {
        if (status == null) {
            throw new IllegalArgumentException("상태 값은 null일 수 없습니다.");
        }

        return switch (status) {
            case "PENDING" -> PENDING;
            case "AUTHORIZED" -> AUTHORIZED;
            case "SUCCESS" -> SUCCESS;
            case "FAILED" -> FAILED;
            case "PARTIALLY_CANCELED" -> PARTIALLY_CANCELED;
            case "CANCELED" -> CANCELED;
            default -> throw new IllegalArgumentException("등록되지 않은 결제 상태입니다 {status: " + status + "}");
        };
    }

    public String getDisplayName() {
        return switch (this) {
            case PENDING -> "결제 진행 중";
            case AUTHORIZED -> "결제 승인됨";
            case SUCCESS -> "결제 성공";
            case FAILED -> "결제 실패";
            case PARTIALLY_CANCELED -> "부분 취소됨";
            case CANCELED -> "전체 취소됨";
        };
    }
}
