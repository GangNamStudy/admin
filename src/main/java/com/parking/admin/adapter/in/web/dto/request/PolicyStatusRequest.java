package com.parking.admin.adapter.in.web.dto.request;

import com.parking.admin.domain.vo.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PolicyStatusRequest {
    private Long id;
    private PolicyStatus policyStatus;
}
