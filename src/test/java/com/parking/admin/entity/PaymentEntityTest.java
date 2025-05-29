package com.parking.admin.entity;

import com.parking.admin.application.command.CreatePaymentCommand;
import com.parking.admin.application.mapper.PaymentMapper;
import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.payment.PaymentEntity;
import com.parking.admin.domain.payment.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PaymentEntityTest {

    @Test
    @DisplayName("PaymentEntity 생성")
    void createPaymentEntity_ShouldCreateEntityWithCorrectValues() {
        // Given
        CreatePaymentCommand command = CreatePaymentCommand.builder()
                .paymentId(1L)
                .identifier("TEST-001")
                .amount(10000L)
                .paymentDate(LocalDateTime.now())
                .status("SUCCESS")
                .orderName("Test Order")
                .paymentMethod("CARD")
                .receiptUrl("http://receipt.url")
                .build();

        PaymentMapper mapper = new PaymentMapper();

        // When
        PaymentEntity entity = mapper.toEntity(command);

        // Then
        assertThat(entity.getPaymentId()).isEqualTo(EntityId.of(command.getPaymentId()));
        assertThat(entity.getIdentifier()).isEqualTo(command.getIdentifier());
        assertThat(entity.getAmount()).isEqualTo(Money.of(command.getAmount()));
        assertThat(entity.getPaymentDate()).isEqualTo(command.getPaymentDate());
        assertThat(entity.getStatus()).isEqualTo(PaymentStatus.of(command.getStatus()));
        assertThat(entity.getStatus().getDisplayName()).isEqualTo("결제 성공");
        assertThat(entity.getOrderName()).isEqualTo(command.getOrderName());
        assertThat(entity.getPaymentMethod()).isEqualTo(command.getPaymentMethod());
        assertThat(entity.getReceiptUrl()).isEqualTo(command.getReceiptUrl());
    }
}
