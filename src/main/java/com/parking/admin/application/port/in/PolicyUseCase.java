package com.parking.admin.application.port.in;

import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.policy.DurationTime;
import com.parking.admin.domain.policy.PolicyInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

public interface PolicyUseCase {
    PolicyInfo updatePolicy(UpdatePolicyCommand command);
    PolicyInfo getPolicy();

    @Getter
    @AllArgsConstructor
    class UpdatePolicyCommand {
        private final Money baseFee;
        private final DurationTime freeTime;
        private final Money additionalFee;
        private final DurationTime additionalTime;
    }
}
