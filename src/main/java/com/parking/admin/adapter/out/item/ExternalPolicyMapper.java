package com.parking.admin.adapter.out.item;

import com.parking.admin.adapter.out.item.dto.ExternalReadPolicyResponse;
import com.parking.admin.adapter.out.item.dto.ExternalUpdatePolicyRequest;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.policy.DurationTime;
import com.parking.admin.domain.policy.PolicyEntity;
import org.springframework.stereotype.Component;

@Component
public class ExternalPolicyMapper {
    public ExternalUpdatePolicyRequest toUpdateRequest(PolicyEntity entity) {
        return new ExternalUpdatePolicyRequest(
                entity.getBaseFee().getAmount(),
                entity.getFreeTime().getDuration(),
                entity.getAdditionalFee().getAmount(),
                entity.getAdditionalTime().getDuration()
        );
    }

    public PolicyEntity toEntity(ExternalReadPolicyResponse response) {
        return new PolicyEntity(
                Money.of(response.getBaseFee()),
                DurationTime.of(response.getFreeTime()),
                Money.of(response.getAdditionalFee()),
                DurationTime.of(response.getAdditionalTime())
        );
    }
}
