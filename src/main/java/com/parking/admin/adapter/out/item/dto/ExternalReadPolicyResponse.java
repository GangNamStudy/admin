package com.parking.admin.adapter.out.item.dto;

import lombok.Getter;

@Getter
public class ExternalReadPolicyResponse {
    private Long baseFee;
    private Long freeTime;
    private Long additionalFee;
    private Long additionalTime;
}
