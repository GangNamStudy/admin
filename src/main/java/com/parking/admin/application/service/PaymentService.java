package com.parking.admin.application.service;

import com.parking.admin.application.port.in.PaymentUseCase;
import com.parking.admin.application.port.out.PaymentPort;
import com.parking.admin.common.UseCase;
import com.parking.admin.domain.payment.DayPaymentInfo;
import com.parking.admin.domain.payment.MonthPaymentInfo;
import com.parking.admin.domain.payment.PaymentEntity;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class PaymentService implements PaymentUseCase {

    private final PaymentPort paymentPort;

    @Override
    public MonthPaymentInfo getMonthAmount(PaymentUseCase.searchPaymentCommand command) {
        List<PaymentEntity> entities = paymentPort.loadByDate(command);
        long total = 0L;

        Long[] days = new Long[32];

        for (PaymentEntity entity : entities){
            LocalDate date = entity.getPaymentDate().toLocalDate();
            int day = date.getDayOfMonth();
            Long amount = entity.getAmount().getAmount();
            if (days[day] == null) {
                days[day] = amount;
            }else days[day] += amount;

            total += entity.getAmount().getAmount();
        }

        List<DayPaymentInfo> infos = new ArrayList<>();

        for (int i = 1; i <= 31; i++) {
            if (days[i] != null) {
                infos.add(new DayPaymentInfo(i, days[i]));
            }
        }

        return new MonthPaymentInfo(infos, total);
    }
}
