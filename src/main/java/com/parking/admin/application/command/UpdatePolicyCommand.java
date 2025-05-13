package com.parking.admin.application.command;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePolicyCommand {
    private Long baseFee;
    private Long freeTime;
    private Long additionalFee;
    private Long additionalTime;
}
