package com.parking.admin.entity;

import com.parking.admin.domain.common.EntityId;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.payment.PaymentEntity;
import com.parking.admin.domain.payment.PaymentStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class PaymentEntityTest {

    @Test
    @DisplayName("PaymentEntity 생성")
    void createPaymentEntity_ShouldCreateEntityWithCorrectValues() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        EntityId paymentId = EntityId.of(1L);
        String identifier = "TEST-001";
        Money amount = Money.of(10000L);
        PaymentStatus status = PaymentStatus.of("SUCCESS");
        String orderName = "Test Order";
        String paymentMethod = "CARD";
        String receiptUrl = "http://receipt.url";

        // When
        PaymentEntity entity = new PaymentEntity(
                paymentId,
                identifier,
                amount,
                now,
                status,
                orderName,
                paymentMethod,
                receiptUrl
        );

        // Then
        assertThat(entity.getPaymentId()).isEqualTo(paymentId);
        assertThat(entity.getIdentifier()).isEqualTo(identifier);
        assertThat(entity.getAmount()).isEqualTo(amount);
        assertThat(entity.getPaymentDate()).isEqualTo(now);
        assertThat(entity.getStatus()).isEqualTo(status);
        assertThat(entity.getStatus().getDisplayName()).isEqualTo("결제 성공");
        assertThat(entity.getOrderName()).isEqualTo(orderName);
        assertThat(entity.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(entity.getReceiptUrl()).isEqualTo(receiptUrl);
    }
}
