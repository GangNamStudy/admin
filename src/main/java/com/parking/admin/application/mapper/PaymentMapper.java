package com.parking.admin.application.mapper;

import com.parking.admin.application.command.CreatePaymentCommand;
import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.payment.PaymentEntity;
import com.parking.admin.domain.payment.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentEntity toEntity(CreatePaymentCommand command) {
        return new PaymentEntity(EntityId.of(command.getPaymentId()),
                command.getIdentifier(),
                Money.of(command.getAmount()),
                command.getPaymentDate(),
                PaymentStatus.of(command.getStatus()),
                command.getOrderName(),
                command.getPaymentMethod(),
                command.getReceiptUrl()
        );
    }
}
