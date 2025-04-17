package com.parking.admin.application.command;

import com.parking.admin.domain.vo.DurationTime;
import com.parking.admin.domain.vo.Money;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePolicyCommand {
    @NonNull private Money baseFee;
    @NonNull private DurationTime freeTime;
    @NonNull private Money additionalFee;
    @NonNull private DurationTime additionalTime;

}
