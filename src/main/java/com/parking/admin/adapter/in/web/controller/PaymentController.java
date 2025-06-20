package com.parking.admin.adapter.in.web.controller;

import com.parking.admin.application.port.in.PaymentUseCase;
import com.parking.admin.domain.payment.MonthPaymentInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/admin/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentUseCase paymentUseCase;

    @GetMapping
    public ResponseEntity<MonthPaymentInfo> searchPayment(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "startTime", required = false) LocalDateTime startTime,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            @RequestParam(value = "endTime", required = false) LocalDateTime endTime
            ) {
        if (startTime == null) startTime = LocalDateTime.now().minusMonths(1);
        if (endTime == null) endTime = LocalDateTime.now();
        return ResponseEntity.ok(paymentUseCase.getMonthAmount(new PaymentUseCase.searchPaymentCommand(startTime, endTime)));
    }
}
