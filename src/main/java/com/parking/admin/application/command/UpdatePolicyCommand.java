package com.parking.admin.application.command;

import com.parking.admin.domain.vo.Duration;
import com.parking.admin.domain.vo.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UpdatePolicyCommand {
    private Money baseFee;
    private Duration freeTime;
    private Money additionalFee;
    private Duration additionalTime;

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
