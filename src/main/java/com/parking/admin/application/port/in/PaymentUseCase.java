package com.parking.admin.application.port.in;

import com.parking.admin.domain.payment.MonthPaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public interface PaymentUseCase {
    MonthPaymentInfo getMonthAmount(searchPaymentCommand command);

    @Getter
    @AllArgsConstructor
    class searchPaymentCommand {
        LocalDateTime startDate;
        LocalDateTime endDate;
    }
}
