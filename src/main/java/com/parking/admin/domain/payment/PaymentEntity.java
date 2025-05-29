package com.parking.admin.domain.payment;

import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PaymentEntity {
    private EntityId paymentId;
    private final String identifier;
    private Money amount;
    private final LocalDateTime paymentDate;
    private final PaymentStatus status;
    private final String orderName;
    private final String paymentMethod;
    private final String receiptUrl;
}