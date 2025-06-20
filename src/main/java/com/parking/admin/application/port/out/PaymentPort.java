package com.parking.admin.application.port.out;

import com.parking.admin.application.port.in.PaymentUseCase;
import com.parking.admin.domain.payment.PaymentEntity;

import java.util.List;

public interface PaymentPort {
    PaymentEntity load(Long id);
    List<PaymentEntity> loadByDate(PaymentUseCase.searchPaymentCommand command);
}
