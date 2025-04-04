package com.parking.admin.application.command;

import com.parking.admin.domain.vo.Duration;
import com.parking.admin.domain.vo.Money;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CreatePolicyCommand {
    private Money baseFee;
    private Duration freeTime;
    private Money additionalFee;
    private Duration additionalTime;

    public void validate() {
        if (baseFee == null || freeTime == null || additionalFee == null || additionalTime == null) {
            throw new IllegalArgumentException("null 값이 들어있습니다.");
        }
    }

}
