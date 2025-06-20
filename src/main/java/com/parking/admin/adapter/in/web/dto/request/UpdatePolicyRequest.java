package com.parking.admin.adapter.in.web.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UpdatePolicyRequest {
    private Long baseFee;
    private Long freeTime;
    private Long additionalFee;
    private Long additionalTime;
}
