package com.parking.admin.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PolicyResponse {
    private Long id;
    private String policyName;
    private Long baseFee;
    private Long feeTime;
    private Long additionalFee;
    private Long additionalTime;
}
