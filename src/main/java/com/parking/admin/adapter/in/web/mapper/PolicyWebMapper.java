package com.parking.admin.adapter.in.web.mapper;

import com.parking.admin.adapter.in.web.dto.request.UpdatePolicyRequest;
import com.parking.admin.adapter.in.web.dto.response.PolicyResponse;
import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.policy.DurationTime;
import com.parking.admin.domain.policy.PolicyInfo;
import org.springframework.stereotype.Component;

@Component
public class PolicyWebMapper {
    public PolicyUseCase.UpdatePolicyCommand toCommand(UpdatePolicyRequest request) {
        return new PolicyUseCase.UpdatePolicyCommand(
                request.getBaseFee() != null ? Money.of(request.getBaseFee()) : null,
                request.getFreeTime() != null ? DurationTime.of(request.getFreeTime()) : null,
                request.getAdditionalFee() != null ? Money.of(request.getAdditionalFee()) : null,
                request.getAdditionalTime() != null ? DurationTime.of(request.getAdditionalTime()) : null
        );
    }

    public PolicyResponse toResponse(PolicyInfo info){
        return new PolicyResponse(
                info.getBaseFee().getAmount(),
                info.getFreeTime().getDuration(),
                info.getAdditionalFee().getAmount(),
                info.getAdditionalTime().getDuration()
        );
    }
}
