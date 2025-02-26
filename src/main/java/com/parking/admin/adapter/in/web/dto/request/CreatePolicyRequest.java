package com.parking.admin.adapter.in.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyRequest {
    private String policyName;
    private int baseFee;
    private int feeTime;
    private int additionalFee;
    private int additionalTime;
}
