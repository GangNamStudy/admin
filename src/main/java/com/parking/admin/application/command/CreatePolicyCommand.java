package com.parking.admin.application.command;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePolicyCommand {
    @NonNull private Long baseFee;
    @NonNull private Long freeTime;
    @NonNull private Long additionalFee;
    @NonNull private Long additionalTime;

}
