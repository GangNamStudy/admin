package com.parking.admin.domain.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DayPaymentInfo {
    private int date;
    private Long dayFee;

}
