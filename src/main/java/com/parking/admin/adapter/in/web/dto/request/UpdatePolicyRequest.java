package com.parking.admin.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdatePolicyRequest {
    private Long baseFee;
    private Long feeTime;
    private Long additionalFee;
    private Long additionalTime;
}
