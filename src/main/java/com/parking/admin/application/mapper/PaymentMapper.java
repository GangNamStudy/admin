package com.parking.admin.application.mapper;

import com.parking.admin.application.command.CreatePaymentCommand;
import com.parking.admin.domain.entity.PaymentEntity;
import com.parking.admin.domain.vo.EntityId;
import com.parking.admin.domain.vo.Money;
import com.parking.admin.domain.vo.PaymentStatus;
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
