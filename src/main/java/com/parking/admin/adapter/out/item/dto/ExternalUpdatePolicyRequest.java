package com.parking.admin.adapter.out.item.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExternalUpdatePolicyRequest {
    private Long baseFee;
    private Long freeTime;
    private Long additionalFee;
    private Long additionalTime;
}
