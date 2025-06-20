package com.parking.admin.adapter.out.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class ExternalPaymentResponse {
    private UUID paymentId;
    private final String identifier;
    private Long amount;
    private final LocalDateTime paymentDate;
    private final String status;
    private final String orderName;
    private final String paymentMethod;
    private final String receiptUrl;
}
