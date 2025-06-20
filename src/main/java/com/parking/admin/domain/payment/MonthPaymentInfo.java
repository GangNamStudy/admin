package com.parking.admin.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MonthPaymentInfo {
    List<DayPaymentInfo> days;
    Long totalAmount;
}

