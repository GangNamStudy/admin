package com.parking.admin.adapter.out.payment;

import com.parking.admin.adapter.out.payment.dto.ExternalPaymentResponse;
import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.payment.PaymentEntity;
import com.parking.admin.domain.payment.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class ExternalPaymentMapper {
    public PaymentEntity toEntity(ExternalPaymentResponse response){
        return new PaymentEntity(
                EntityId.of(response.getPaymentId()),
                response.getIdentifier(),
                Money.of(response.getAmount()),
                response.getPaymentDate(),
                PaymentStatus.of(response.getStatus()),
                response.getOrderName(),
                response.getPaymentMethod(),
                response.getReceiptUrl()
        );
    }
}
