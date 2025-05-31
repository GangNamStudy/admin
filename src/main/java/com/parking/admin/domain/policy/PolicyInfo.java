package com.parking.admin.domain.policy;

import com.parking.admin.domain.common.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PolicyInfo {
    private final Money baseFee;
    private final DurationTime freeTime;
    private final Money additionalFee;
    private final DurationTime additionalTime;
}
