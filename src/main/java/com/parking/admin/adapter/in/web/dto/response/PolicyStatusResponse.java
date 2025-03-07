package com.parking.admin.adapter.in.web.dto.response;

import com.parking.admin.domain.vo.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PolicyStatusResponse {
    private final Long id;
    private final PolicyStatus policyStatus;
}
