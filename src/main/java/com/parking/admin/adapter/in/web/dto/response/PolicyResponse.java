package com.parking.admin.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PolicyResponse {
    private Long baseFee;
    private Long freeTime;
    private Long additionalFee;
    private Long additionalTime;
}
