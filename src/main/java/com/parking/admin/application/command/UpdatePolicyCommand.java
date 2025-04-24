package com.parking.admin.application.command;

import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePolicyCommand {
    private Money baseFee;
    private DurationTime freeTime;
    private Money additionalFee;
    private DurationTime additionalTime;

    public boolean hasBaseFee() {
        return baseFee != null;
    }

    public boolean hasFreeTime() {
        return freeTime != null;
    }

    public boolean hasAdditionalFee() {
        return additionalFee != null;
    }

    public boolean hasAdditionalTime() {
        return additionalTime != null;
    }
}
